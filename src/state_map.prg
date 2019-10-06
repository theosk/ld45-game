PROCESS state_map()

PRIVATE hidden;

BEGIN
    while (game.state != STATE_MAP_ID) frame; end
    map.ui_id = state_map_ui();
    hidden = 0;

    write(game.font, 320, 460, 4, map.text_location, 1);
    write(0, 320, 8, 1, map.text_inventory, 1);
    state_map_update_inventory_text();

    loop

        if (!hidden)
            if (map.text_reset_count > 0)
                map.text_reset_count--;
            else
                map.text_location = "Click where you want to go.";
            end
        end
        
        if (map.next_destination != LOCATION_NONE)
            state_map_hide();
            hidden = 1;
            state_map_travel(map.next_destination);
        end

        if (game.state == STATE_MAP_ID && hidden)
            state_map_show();
            hidden = 0;
        end

        frame;
    end
END


PROCESS state_map_ui()

BEGIN
    x = 320;
    y = 240;
    z = 1;
    graph = img_bg_map;

    state_map_place(72, 56, LOCATION_SHORE, "The shore");
    state_map_place(170, 116, LOCATION_WOODS, "The woods");
    state_map_place(252, 195, LOCATION_TOWN, "The town");
    state_map_place(268, 309, LOCATION_INN, "The inn");
    state_map_place(387, 288, LOCATION_JEWEL_SHOP, "Jewels shop");
    state_map_place(180, 400, LOCATION_WEAPON_SHOP, "Weapons shop");
    state_map_place(322, 400, LOCATION_CASTLE, "The castle");
    state_map_place(360, 90, LOCATION_BANDIT_CAMP, "Bandit camp");
    state_map_place(560, 72, LOCATION_BANDIT_BOSS, "Bandit boss cave");
    state_map_place(61, 256, LOCATION_ORC_SETTLEMENT, "Orc settlement");
    state_map_place(66, 380, LOCATION_ORC_TREASURE_ROOM, "Orc treasure room");
    state_map_place(590, 320, LOCATION_DEMON_LAIR, "Demon's lair");
    state_map_place(488, 392, LOCATION_DEMON_FIELDS, "Demon fields");
    state_map_place(488, 392, LOCATION_MOUNTAIN, "Mountain");

    loop
        frame;
    end
END

PROCESS state_map_place(x, y, destination, string name)
PRIVATE 

BEGIN
    z = 0;
    state_map_place_frame();
    loop
        flags = 4;
        graph = img_marker_unknown;
        if (map.area_unlocked[destination])
            graph = img_marker[destination];
            if(collision(type mouse))
                flags = 0;
                map.text_reset_count = 60;
                map.text_location = name;
            end

            if(collision(type mouse) && mouse.left)
                repeat frame; until(!mouse.left)
                if(collision(type mouse))
                    map.next_destination = destination;
                end
            end
        end

        frame;
    end
END

PROCESS state_map_place_frame()

BEGIN
    x = father.x;
    y = father.y;
    z = -1;
    graph = img_marker_frame;
    loop
        frame;
    end
END


function state_map_show()

BEGIN
    signal(map.ui_id, s_wakeup_tree);
    state_map_update_inventory_text();
END

function state_map_hide()

BEGIN
    signal(map.ui_id, s_sleep_tree);
    map.text_location = "";
    map.text_inventory = "";
END


FUNCTION state_map_travel(location)

BEGIN
    sound_play(snd.select);
    game.state = STATE_DIALOG_ID;

    switch(location) // TODO: Functions to check which script to call for every location based on quest variables
        case LOCATION_SHORE:
            script_shore();
        end

        case LOCATION_WOODS:
            script_merchant_attack();
        end

    end

    map.next_destination = LOCATION_NONE;
END

FUNCTION state_map_update_inventory_text()
BEGIN
    map.text_inventory = "I have: " + itoa(inventory.gold) + " coins";
    if(inventory.dagger != 0)
        map.text_inventory += ", a dagger";
    end

    if(inventory.sword != 0)
        map.text_inventory += ", a sword";
    end

    if(inventory.weddingRing != 0)
        map.text_inventory += ", a ring";
    end

    if(inventory.amulet != 0)
        map.text_inventory += ", an amulet";
    end

    if(inventory.shield != 0)
        map.text_inventory += ", a shield";
    end

    if(inventory.weddingRing != 0)
        map.text_inventory += ", a wedding ring";
    end


    map.text_inventory = upper(map.text_inventory);

END