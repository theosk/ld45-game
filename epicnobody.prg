PROGRAM EpicNobody;

include "src/globals.prg";

include "src/state_dialog.prg";
include "src/scripts/intro.prg";
include "src/scripts/merchant_attack.prg";


BEGIN

    //VIRTUALRESOLUTION_SET(1280, 1024, 1, 0); 
    mode_set(640, 480, 32);
    
    window_set_title("Epic Nobody");
    set_fps(60, 0);

    load_assets();

  
    state_dialog();
    game.state = STATE_DIALOG_ID;
    frame(2000);

    script_intro();
    script_merchant_attack();

    loop
        if (key(_esc)) break; end
        frame;
    end

    exit(0,0);

END


FUNCTION load_assets()

BEGIN
    game.font = fnt_load("assets/font.fnt"); 
    img_bg = image_load("assets/images/bg.png");
    mouse.graph = image_load("assets/images/pointer.png");
    map_set_center(0, mouse.graph, 9, 11);
END