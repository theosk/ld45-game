FUNCTION script_place_inn()

BEGIN
    game.state = STATE_DIALOG_ID;
    
    state_dialog_clear();

    if(quests.main == STATUS_TOWN_VISITED)
        script_place_inn_first_time_text();
    end

    game.state = STATE_MAP_ID;
END


FUNCTION script_place_inn_first_time_text()

BEGIN
    state_dialog_putline("You rest.", 400);
    state_dialog_putline("Next morning, you talk to the innkeeper.", 400);
    state_dialog_putline("[Innkeeper] ''This city is not as safe as it looks.", 400);
    state_dialog_putline("We are fine inside the walls, but there's all kind of", 400);
    state_dialog_putline("evil doers surrounding the city. The king is offering", 400);
    state_dialog_putline("some good gold if you can hunt the bandits. And that is", 400);
    state_dialog_putline("just the tip of the iceberg.''", 400);

    state_dialog_waitkey();
    state_dialog_clear();

    state_dialog_putline("Some locations have been unlocked.", 400);
    state_dialog_waitkey();
    state_dialog_clear();

    quests.main = STATUS_TOWN_VISITED;
    map.area_unlocked[LOCATION_WEAPON_SHOP] = 1;
    map.area_unlocked[LOCATION_JEWEL_SHOP] = 1;
    map.area_unlocked[LOCATION_BANDIT_CAMP] = 1;
END