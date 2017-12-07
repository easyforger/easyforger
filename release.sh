#!/usr/bin/env bash

set -e          # exit on error
set -u          # undefined vars as errors
set -o pipefail # fail if any commando in a series of piped commands fail

usage() {
    echo -e "\nUsage:"
    echo "./release.sh [-d|-r|-s]"
    echo -e "\nWhere:"
    echo "-d -> dryRun"
    echo "-r -> publish a release version"
    echo "-s -> publish a snapshot version"

    exit 1
}

read_version() {
    grep "version = \"[0-9.A-Z-]*\"" build.gradle | head -1 | cut -d = -f 2- | tr -d \ \"
}

confirm() {
    read -p "$1 (y/n) " -n 1 choice
    echo ""

    case "$choice" in
        y|Y ) echo "" ;;
        *   ) exit 1 ;;
    esac
}

# inspiration: https://stackoverflow.com/questions/6245293/extract-version-number-from-file-in-shell-script
roll_version() {
    version=$1
    a=( ${version//./ } )                   # replace points, split into array

    len=${#a[@]}
    ((a[len-1]++))                          # increment revision (or other part)

    version=$(printf "%s." "${a[@]}")

    echo ${version::-1}
}

release() {
    if [ ! -f gradle.properties ] || ! grep -q myBintrayUser gradle.properties || ! grep -q myBintrayKey gradle.properties
    then
        echo "Bintray credentials not found, they must be added to gradle.properties"
        exit 1
    fi

    CURR_VERSION=$(read_version)
    RELEASE_VERSION=$(echo $CURR_VERSION | sed 's/-SNAPSHOT//g')

    confirm "About to release version '$RELEASE_VERSION' - proceed?"

    sed -i "s/$CURR_VERSION/$RELEASE_VERSION/" build.gradle

    echo "Switch to a release work branch"
    git co -b release-$RELEASE_VERSION

    echo "Commit the new version"
    git add build.gradle
    git commit -m "Update version to release"

    echo "Generate and upload artifacts to bintray"
    ./gradlew bintrayUpload

    echo "Push release version to github"
    git push

#    TODO: automate this
    read -p "Create a new github release and press enter to continue (draw new release) - this also creates the git tag"

#    TODO: automate this
    read -p "Close the current milestone in github and press enter to continue"

    echo "Roll the version and add -SNAPSHOT back"
    MC_VERSION=$(echo $RELEASE_VERSION | cut -d- -f 2)
    EF_VERSION=$(echo $RELEASE_VERSION | cut -d- -f 1)
    NEXT_VERSION=$(roll_version $EF_VERSION)

    sed -i "s/$RELEASE_VERSION/$NEXT_VERSION-$MC_VERSION-SNAPSHOT/" build.gradle

    echo "Commit the new version"
    git add build.gradle
    git commit -m "Update version to the next dev version"

    echo "Push all the changes to github"
    git push

#    TODO: send announcements to twitter, the mailing list and the forums at forum.easyforger.com
#    TODO: update ef-mod-template, generate the tag and upload the template link on the easyforger.com website
}

if [ $# -eq 0 ]; then
    echo "No arguments provided"
    usage
    exit 1
fi

while getopts ":hdrs" opt; do
    case $opt in
        d)
            ./gradlew clean build bintrayUpload --dry-run
            ;;
        r)
            release
            ;;
        s)
            # TODO: https://github.com/easyforger/easyforger/issues/111
            echo "Uploading a snapshot version is not supported yet" >&2
            ;;
        h | *)
            echo "Invalid option: -$OPTARG" >&2
            usage
            ;;
    esac
done
