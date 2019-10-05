PROCESS state_map()

PRIVATE hidden;

BEGIN
    while (game.state != STATE_MAP_ID) frame; end
    map.ui_id = state_map_ui();

    loop
        frame;
    end
END


PROCESS state_map_ui()

BEGIN
    // TODO: add items here!
    loop
        frame;
    end
END


function state_map_show()

BEGIN
    signal(map.ui_id, s_wakeup_tree);
END

function state_map_hide()

BEGIN
    signal(map.ui_id, s_sleep_tree);
END

