FUNCTION script_shore()
BEGIN
    game.state = STATE_DIALOG_ID;
    
    switch(quests.main)
        case STATUS_START:
            script_shore_text_intro();
        end

        case STATUS_SHORE_VISITED:
            script_shore_text();
        end

    end
   
    state_dialog_clear();
    game.state = STATE_MAP_ID;
    map.area_unlocked[LOCATION_WOODS] = 1;
END

FUNCTION script_shore_text();

BEGIN
    state_dialog_putline("You arrive to the shore.", 400);
    state_dialog_putline("There is a nearby forest.", 400);
    state_dialog_putline("Nothing to do here at the moment.", 400);

    state_dialog_waitkey();
END


FUNCTION script_shore_text_intro();

BEGIN
    state_dialog_putline("Your ship was attacked by pirates and sunk in the middle of.", 400);
    state_dialog_putline("the night. You swim away until you are exhausted.", 400);
    state_dialog_putline("", 400);
    state_dialog_putline("You wake up in the shore. Looks like you reached your", 400);
    state_dialog_putline("destination after all. You see a forest nearby.", 400);

    quests.main = STATUS_SHORE_VISITED;

    state_dialog_waitkey();
END