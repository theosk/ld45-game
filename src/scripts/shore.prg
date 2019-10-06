FUNCTION script_shore()
BEGIN
    game.state = STATE_DIALOG_ID;
    
    switch(quests.main)
        case STATUS_START:
            script_shore_text_intro();
        end


        case STATUS_FRAGMENTS_SEARCH:
            if (inventory.shoreFragment)
                script_shore_text();
            else
                script_shore_fragments_text();
            end
        end

        default:
            if (inventory.treasureMap && !inventory.shield)
                script_shore_text_get_shield();
            else
                script_shore_text();
            end
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


FUNCTION script_shore_text_get_shield();

BEGIN
    state_dialog_putline("You make use of your brand new treasure map.", 400);
    state_dialog_putline("It says the treasure is buried right under the huge X mark.", 400);
    state_dialog_putline("You start digging with your own hands, as usual...", 400);
    state_dialog_putline("You find an old chest. Inside it, there's a nice shield.", 400);
    sound_play(snd.win);
    state_dialog_putline("The shield gives you 1 extra attack point. Cool, uh?", 400);
    inventory.shield = 1;
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

FUNCTION script_shore_fragments_text();
PRIVATE
    int result;
BEGIN
    state_dialog_putline("You search for the fragment location. You start digging", 400);
    state_dialog_putline("with your own hands. You didn't even get a shovel.", 400);
    state_dialog_putline("After a long while, you get the damn crystal piece.", 400);
    state_dialog_putline("[Beach mugger]:''You! Give me whatever that is! Now!''", 400);
    state_dialog_putline("Great. Now you have to teach this loser a lesson.", 400);
    result = state_battle_start("Beach mugger", rand(1,2));
    if (result)
        state_dialog_putline("The mugger flees crying. You feel bad", 400);
    else 
        state_dialog_putline("The mugger steal some of your coins and flees.", 400);
        inventory.gold *= 0.8;
    end
    
    state_dialog_putline("");
    sound_play(snd.win);
    inventory.shoreFragment = 1;
    state_dialog_putline("You retrieve the DARKNESS crystal fragment!", 400);

    state_dialog_waitkey();
END
