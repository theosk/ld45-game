PROGRAM EpicNobody;

include "src/globals.prg";

include "src/state_dialog.prg";
include "src/state_battle.prg";
include "src/state_map.prg";

include "src/scripts/intro.prg";
include "src/scripts/shore.prg";
include "src/scripts/merchant_attack.prg";
include "src/scripts/place_town.prg";
include "src/scripts/place_inn.prg";
include "src/scripts/place_jewel_shop.prg";
include "src/scripts/place_weapon_shop.prg";
include "src/scripts/place_castle.prg";
include "src/scripts/place_bandit_camp.prg";
include "src/scripts/place_bandit_boss.prg";
include "src/scripts/place_orc_settlement.prg";
include "src/scripts/place_orc_treasure_room.prg";
include "src/scripts/place_demon_lair.prg";
include "src/scripts/place_demon_fields.prg";
include "src/scripts/place_mountain.prg";

BEGIN

    //VIRTUALRESOLUTION_SET(1280, 1024, 1, 0); 
    set_mode(640, 480, 16);
    
    set_title("Epic story of a nobody");
    set_fps(60, 0);

    load_assets();

  
    state_dialog();
    state_map();
    game.state = STATE_DIALOG_ID;
    frame(2000);

    logo();
    script_intro();

    loop
        if (inventory.gold > 9999) inventory.gold = 9999; end
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
    play_wav(snd.ask, 0, 0);
    delete_text(z);

    from size = 100 to 0;
        angle+=1000;
        frame(33);
    end

    repeat frame; until(!key(_space) && !mouse.left);
    
END


FUNCTION load_assets()

BEGIN
    game.font = load_fnt("assets/font-fenix.fnt"); 
    img_bg = load_png("assets/images/bg.png");
    img_bg_map = load_png("assets/images/map.png");
    img_bg_intro = load_png("assets/images/bg_intro.png");
    img_bg_bandit_intro = load_png("assets/images/bg_bandit_intro.png");
    img_bg_dialog = img_bg_intro;
    img_logo = load_png("assets/images/logo.png");

    mouse.graph = load_png("assets/images/pointer.png");
    set_center(0, mouse.graph, 9, 11);
    img_marker_unknown = load_png("assets/images/marker_unknown.png");
    img_marker_frame = load_png("assets/images/marker_frame.png");

    img_marker[LOCATION_SHORE] = load_png("assets/images/location_shore.png");
    img_marker[LOCATION_WOODS] = load_png("assets/images/location_woods.png");
    img_marker[LOCATION_TOWN] = load_png("assets/images/location_town.png");
    img_marker[LOCATION_BANDIT_CAMP] = load_png("assets/images/location_bandit_camp.png");
    img_marker[LOCATION_BANDIT_BOSS] = load_png("assets/images/location_bandit_leader.png");
    img_marker[LOCATION_ORC_SETTLEMENT] = load_png("assets/images/location_orc_settlement.png");
    img_marker[LOCATION_ORC_TREASURE_ROOM] = load_png("assets/images/location_orc_treasure.png");
    img_marker[LOCATION_DEMON_FIELDS] = load_png("assets/images/location_demon_fields.png");
    img_marker[LOCATION_DEMON_LAIR] = load_png("assets/images/location_demon_lord.png");
    img_marker[LOCATION_INN] = load_png("assets/images/location_inn.png");
    img_marker[LOCATION_JEWEL_SHOP] = load_png("assets/images/location_jewel_store.png");
    img_marker[LOCATION_WEAPON_SHOP] = load_png("assets/images/location_weapon_store.png");
    img_marker[LOCATION_MOUNTAIN] = load_png("assets/images/location_mountain.png");
    img_marker[LOCATION_CASTLE] = load_png("assets/images/location_castle.png");

    snd.hit[0] = load_wav("assets/sound/hit1.wav");
    snd.hit[1] = load_wav("assets/sound/hit2.wav");
    snd.hit[2] = load_wav("assets/sound/hit3.wav");
    snd.hit[3] = load_wav("assets/sound/hit4.wav");
    snd.hit[4] = load_wav("assets/sound/hit5.wav");
    snd.hit[5] = load_wav("assets/sound/hit6.wav");
    snd.hit[6] = load_wav("assets/sound/hit7.wav");
    snd.win = load_wav("assets/sound/win.wav");
    snd.coin = load_wav("assets/sound/coin.wav");
    snd.select = load_wav("assets/sound/select.wav");
    snd.ask = load_wav("assets/sound/ask.wav");

END