FUNCTION script_place_mountain();

BEGIN
    game.state = STATE_DIALOG_ID;
    
    switch(quests.main)

        case STATUS_FRAGMENTS_SEARCH:
            if (inventory.mountainFragment)
                script_place_mountain_text();
            else
                script_place_mountain_fragments_text();
            end
        end

        default:
            script_place_mountain_text();
        end


    end
   
    state_dialog_clear();
    game.state = STATE_MAP_ID;
END

FUNCTION script_place_mountain_fragments_text()

BEGIN
    state_dialog_putline("You climb the mountain and get the fragment.", 400);
    state_dialog_putline("It was really, really high. The reward better be huge,", 400);
    state_dialog_putline("or else you are going to murder the king...", 400);
    state_dialog_waitkey();
    inventory.mountainFragment = 1;
END


FUNCTION script_place_mountain_text()

BEGIN
    state_dialog_putline("There's no way you are going to climb this mountain", 400);
    state_dialog_putline("for no reason...", 400);
    state_dialog_waitkey();
END