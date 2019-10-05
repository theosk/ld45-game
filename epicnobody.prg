PROGRAM EpicNobody;

include "src/globals.prg";

include "src/state_dialog.prg";
include "src/state_battle.prg";
include "src/state_map.prg";

include "src/scripts/intro.prg";
include "src/scripts/merchant_attack.prg";


BEGIN

    //VIRTUALRESOLUTION_SET(1280, 1024, 1, 0); 
    mode_set(640, 480, 32);
    
    window_set_title("Epic story of a nobody");
    set_fps(60, 0);

    load_assets();

  
    state_dialog();
    state_map();
    game.state = STATE_DIALOG_ID;
    frame(2000);

    logo();
    script_intro();
    script_merchant_attack();

    loop
        if (key(_esc)) break; end
        frame;
    end

    exit(0,0);

END

FUNCTION logo()

BEGIN
    graph = img_logo;
    x = 320;
    //put_sfreen(0, img_bg);

    z = write(game.font, 320, 360, 4, "- Press SPACE or LMB -");

    repeat 
        y = 200 + 0.01* sin(timer*1000);
        frame; 
    until(key(_space) || mouse.left);

    delete_text(z);

    from size = 100 to 0;
        angle+=1000;
        frame(33);
    end

    repeat frame; until(!key(_space) && !mouse.left);
    
END


FUNCTION load_assets()

BEGIN
    game.font = fnt_load("assets/font.fnt"); 
    img_bg = image_load("assets/images/bg.png");
    img_logo = image_load("assets/images/logo.png");

    mouse.graph = image_load("assets/images/pointer.png");
    map_set_center(0, mouse.graph, 9, 11);
END