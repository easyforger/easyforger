
==================================== Version 0.5.1 ====================================

* Updated forge to 1.10.2-12.18.3.2185;
* Changed `EFItemFood.addPotionEffect` to take a `PotionEffect` instead of individual parameters;
* Renamed `CustomCreeper`, `CustomSkeleton` and `CustomZombie` to `EFCustomCreeper`, `EFCustomSkeleton` and `EFCustomZombie`.
* Replaced `common.heldItem` with `common.heldItemMainHand` and `common.heldItemOffHand`

===================================== Version 0.5 =====================================

### Main changes

This version adds support for _MC 1.10.2_. Due to the number of fixes required,
some of the changes are breaking changes. Here is a summary of what is new in
this release:

* The `trait VanillaChests` has been deleted, and will be added again in the future (see https://github.com/easyforger/easyforger/issues/72);
* Sample mod `VanillaChestsMod` was also deleted;
* All samples were updated to use the new uppercase constants from MC. For example, `Items.diamond` is now called `Items.DIAMOND`.
  This also includes `Blocks`, `Material` etc.
* _EasyForger_'s `ArmorType` was removed - MC's `EntityEquipmentSlot` should be used now;
* `EFItemFood.addPotionEffect` now gets a `potion` instead of a `potionId`;
* `creatures` DSL for changing vanilla mobs now gets a reference to the mod doing the customization;
* Added a few more tests to `easyforger-autotester`;

### Known issues:

Due to large number of changes required during the upgrade to support MC 1.10.2, some
features ended up not working properly and will required a bigger effort to be supported
again. Those will come in future versions. The main issues are listed below:

* Configuring the mob's `dropItem` does not work anymore, due to the new Loot Tables mechanism.
  Follow this issue for more information: https://github.com/easyforger/easyforger/issues/73;
* Off-hand is not supported when modding vanilla mobs. Follow this issue for
  updates: https://github.com/easyforger/easyforger/issues/63;
* Declaring mob ids when registering mobs is now mandatory for MinecraftForge and we use the Ids
  from 10101 to 10103 internally. This means that the modder cannot use those Ids if he decides
  to create mobs directly with forge code, otherwise this will generate a conflict when running the mod.
