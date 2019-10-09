
FUNCTION script_merchant_attack()

BEGIN
    if(quests.main ==  STATUS_FRAGMENTS_SEARCH)
        if (!inventory.forestFragment)
            script_woods_text();
        else
            dialog_putline("You should be finding the other fragments...", 400);
            state_dialog_waitkey();
        end

        game.state = STATE_MAP_ID;
        return;
    end

    switch (quests.saveMerchant)
        case STATUS_START:
            img_bg_dialog = img_bg_bandit_intro;
            script_merchant_attack_text();
            state_dialog_ask("Will you fight the bandit?");
            img_bg_dialog = img_bg_intro;
            if(dialog.chosen_answer == 1)
                // Save the merchant
                state_battle_start("the bandit", 0);
                script_merchant_attack_text_ended();
                quests.saveMerchant = STATUS_ENDED;
            else 
                // Let the merchant die
                script_merchant_attack_text_failed();
                quests.saveMerchant = STATUS_FAILED;
            end

            inventory.dagger = 1;
            quests.main = STATUS_WOODS_VISITED;
        end

        default:
            dialog_putline("You get to the forest.", 400);
            dialog_putline("There's nothing going on here right now...", 400);
            dialog_putline("You do some backflips and leave.", 400);
            state_dialog_waitkey();
        end

    end

    state_dialog_clear();
    map.area_unlocked[LOCATION_TOWN] = 1;
    game.state = STATE_MAP_ID;
END

FUNCTION script_merchant_attack_text();

BEGIN
    dialog_putline("You enter the forest, and as soon as you do, you hear nearby", 400);
    dialog_putline("screams. A merchant is being mugged by a bandit. You see some", 400);
    dialog_putline("dead guards on the ground. The bandit is seriously injured.", 400);
    dialog_putline("He must have killed the guards.", 400);
    state_dialog_waitkey();
    state_dialog_clear();
    dialog_putline("[BANDIT]: ''It's over! Surrender if you don't want to die!''", 400);
    dialog_putline("They don't notice your presence. The merchant refuses the offer", 400);
    dialog_putline("and grabs a little knife from one of the dead bodies.", 400);
    state_dialog_waitkey();
    state_dialog_clear();
    dialog_putline("[MERCHANT]: ''I... I am not scared of the likes of you...''", 400);
    dialog_putline("He is obviously lying. You could intervene and save his life.", 400);
    dialog_putline("You see a dagger on the ground, conveniently near.", 400);
    dialog_putline("Or you could let them kill each other and steal their goods...", 400);    
END

FUNCTION script_merchant_attack_text_ended()

BEGIN
    state_dialog_clear();
    dialog_putline("The bandit falls to the ground, dead.", 400);
    dialog_putline("You turn to the merchant, expecting some kind of gratitude.", 400);
    dialog_putline("even if it's just a ''thank you''.", 400);

    dialog_putline("", 400);
    dialog_putline("[Merchant]: ''Step back, filthy peasant! You may have saved my", 400);
    dialog_putline("stuff, but you still stink.''", 400);

    dialog_putline("", 400);
    dialog_putline("You look at that asshole perplexed, still holding your dagger.", 400);
    dialog_putline("He notices that and is scared again.", 400);
    dialog_putline("[Merchant]: ''But... you know... I was carrying so much gold.", 400);
    dialog_putline("And now I get to keep it all. I'm thankful for what you did", 400);
    dialog_putline("so if you ever come to the city, feel free to visit my store!", 400);
    dialog_putline("I'll let you buy whatever you want at a regular price, I'll just", 400);
    dialog_putline("pretend you are not a smelly loser!''", 400);
 
  
    dialog_putline("", 400);
    dialog_putline("He walks away. You should head to the town, you need a rest.", 400);

    state_dialog_waitkey();
    state_dialog_clear();
END

FUNCTION script_merchant_attack_text_failed()

BEGIN
    dialog_putline("", 400);
    dialog_putline("You hide behind some bushes and wait while they fight.", 400);
    dialog_putline("The merchant swings his dagger and fails to land a hit.", 400);
    dialog_putline("The bandit proceeds to stab the merchant countless times.", 400);
    dialog_putline("They both die shortly after due to their wounds.", 400);

    dialog_putline("", 400);
    dialog_putline("You approach the merchant and inspect the body.", 400);
    dialog_putline("He was still barely alive. He tries to talk, only to drown", 400);
    dialog_putline("in his own blood.", 400);
    
    dialog_putline("", 400);
    dialog_putline("You pick up a dagger.", 400);
    play_wav(snd.win, 0, 0);

    dialog_putline("You find 400 coins in his bags.", 400);
    inventory.gold += 400;
    play_wav(snd.coin, 0, 0);

    state_dialog_waitkey();
    state_dialog_clear();
END


FUNCTION script_woods_text()

BEGIN
    dialog_putline("", 400);
    dialog_putline("You search for the fire fragment. You find it.", 400);
    dialog_putline("That was easy. Here, have some coins too!", 400);
    play_wav(snd.coin, 0, 0);
    dialog_putline("You get 10 coins just because!.", 400);
    inventory.gold +=10;
    inventory.forestFragment = 1;

    state_dialog_waitkey();
    state_dialog_clear();
END

