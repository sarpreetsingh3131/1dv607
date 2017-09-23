The grade-4 task is using the same base for grade-3's model and rules.

However, We created three views in order to provide more functionalities such as choosing different game modes and language.
Unlike grade 3 task, it is now possible to change language between English <-> Swedish during the runtime,
and user can choose the modes (soft-17, basic hit, American new game, International new game) on the settings page.

For three views, three different controllers are implemented to share the responsibility for each pages.

To share information of selected language and selected game mode, these variables are defined in static.

Other controllers are extending the 'main' controller which has all associations with all the classes in view package.
Moreover main controller starts the main program with stage as an argument, which is later on used for displaying other pages.

Another change we made is changing card value 'knight' to 'jack', because actual card have 'J' as Jack instead of Knights.
It needed to follow french playing cards' naming in order to match each card value with online image link.

The progamme may have some delay due to its dependency on online images. It takes time to load images from remote location.

Should you have further questions, feel free to contact us.

//Sarpreet & Songho.
