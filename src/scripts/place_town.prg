FUNCTION script_place_town()

BEGIN
    game.state = STATE_DIALOG_ID;
    
    state_dialog_clear();

    if(quests.main == STATUS_WOODS_VISITED)
        script_place_town_first_time_text();
    end

    game.state = STATE_MAP_ID;
END


FUNCTION script_place_town_first_time_text()

BEGIN
    dialog_putline("You finally reach the town.", 400);
    dialog_putline("The guards at the gate look at you and let you in.", 400);
    state_dialog_waitkey();
    state_dialog_clear();

    dialog_putline("This place feels safer than anything you have visited before.", 400);
    dialog_putline("It's midnight. The pubs are crowded. You see some shops.", 400);
    dialog_putline("You keep walking until you find the inn.", 400);
    state_dialog_waitkey();
    state_dialog_clear();

    quests.main = STATUS_TOWN_VISITED;
    map.area_unlocked[LOCATION_INN] = 1;
END