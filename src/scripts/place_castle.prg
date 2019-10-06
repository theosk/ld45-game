FUNCTION script_place_castle();

BEGIN
    game.state = STATE_DIALOG_ID;

    if (quests.main == STATUS_BANDITS_DEFEATED)
        script_place_castle_text_bandits_reward();
    end

    if(quests.main == STATUS_FRAGMENTS_SEARCH)
        x = inventory.mountainFragment + inventory.shoreFragment + inventory.forestFragment;
        if (x < 3)
            script_place_castle_text_fragments_search();
        else
            script_place_castle_text_fragments_end();
        end
    end

    game.state = STATE_MAP_ID;
END

FUNCTION script_place_castle_text_bandits_reward()

BEGIN
    state_dialog_putline("You go enter the castle and go through the huge halls.", 400);
    state_dialog_putline("The guards stare at you, ready to attack if you dare to", 400);
    state_dialog_putline("do something stupid.", 400);
    state_dialog_putline("''Maybe I could beat those pretentious jerks'' you think.", 400);
    state_dialog_putline("But you came to claim your reward.", 400);
    state_dialog_putline("The king is waiting for you. After a short chat, you are.", 400);
    state_dialog_putline("rewarded 300 coins.", 400);
    
    inventory.gold += 300;

    state_dialog_putline("You learn that bandits are the lesser problem around. Orcs", 400);
    state_dialog_putline("and demons roam the surroundings of the realm.", 400);
    
    state_dialog_putline("The war for the magic crystals has reached this land...", 400);
    state_dialog_putline("The main crystals are the most powerful ones, but small", 400);
    state_dialog_putline("fragments of the old ones remain scattered arount the", 400);
    state_dialog_putline("world.", 400);
    state_dialog_putline("Three fragments are hidden around the kingdom. Orcs and", 400);
    state_dialog_putline("demons are searching for them. But the king was saving his", 400);
    state_dialog_putline("trump card. A family treasure. An ancient map showing the", 400);
    state_dialog_putline("exact location of the crystal fragments.", 400);
    state_dialog_putline("Your new mission is to find them before the monsters do.", 400);
    quests.main = STATUS_FRAGMENTS_SEARCH;

    map.area_unlocked[LOCATION_MOUNTAIN] = 1;
    map.area_unlocked[LOCATION_ORC_SETTLEMENT] = 1;
    map.area_unlocked[LOCATION_DEMON_FIELDS] = 1;
    
    state_dialog_waitkey();
END

FUNCTION script_place_castle_text_fragments_search()

BEGIN
    state_dialog_clear();
    if(!inventory.mountainFragment)
        state_dialog_putline("[King]: ''The EARTH fragment is located in the MOUNTAIN''", 400);
    end

    if(!inventory.shoreFragment)
        state_dialog_putline("[King]: ''The DARKNESS fragment is buried in the SHORE''", 400);
    end

    if(!inventory.forestFragment)
        state_dialog_putline("[King]: ''The FIRE fragment is hidden in the WOODS''", 400);
        state_dialog_putline("[King]: ''Not the safest place if you ask me...''", 400);
    end

    x = inventory.mountainFragment + inventory.shoreFragment + inventory.forestFragment;
    if (x == 0)
        state_dialog_putline("[King]: ''Now go! You have too much work to do!''", 400);
    elseif (x==1)
        state_dialog_putline("[King]: ''Now get out and bring me the rest!''", 400);
    else
        state_dialog_putline("[King]: ''Go finish your job already!''", 400);
    end

    state_dialog_waitkey();
END

FUNCTION script_place_castle_text_fragments_end()

BEGIN
    state_dialog_putline("The kings commands the guards to leave the throne room. He", 400);
    state_dialog_putline("can't trust anyone when the magic crystals are here at last.", 400);
    state_dialog_putline("You finally hand him the three crystal fragments.", 400);
    state_dialog_putline("[King]: ''You did well... mortal!''", 400);
    state_dialog_putline("[YOU]: ''Whaaaat?!''", 400);
    state_dialog_putline("The king becomes a bit blurry, and is soon surrounded by smoke.", 400);
    state_dialog_putline("The smoke fades away, revealing the demon king itself. After a", 400);
    state_dialog_putline("brief moment of shock, you rush towards him, but the demon is", 400);
    state_dialog_putline("able to teleport away before you reach him, leaving behind", 400);
    state_dialog_putline("what used to be his human body.", 400);
    state_dialog_putline("Now the demon is gone and there's a king corpse in the room.", 400);
 
    state_dialog_putline("Nobody saw what happened. You exit through a window and escape.", 400);
    state_dialog_putline("The only thing you can do is killing the demon before he uses.", 400);
    state_dialog_putline("the crystal magic for his evil duties..", 400);
    state_dialog_waitkey();
    quests.main = STATUS_DEMON_KING;
       
END