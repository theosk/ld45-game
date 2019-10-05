
FUNCTION script_merchant_attack()

BEGIN
    script_merchant_attack_text();
    state_dialog_ask("Will you fight the bandit?");
    if(dialog.chosen_answer == 1)
        // Save the merchant
        state_battle_start("Bandit", 0);
        quests.saveMerchant = STATUS_ENDED;
    else 
        // Let the merchant die
        quests.saveMerchant = STATUS_FAILED;
    end

        state_dialog_clear();
END

FUNCTION script_merchant_attack_text();

BEGIN
    state_dialog_putline("You enter the forest, and as soon as you do, you hear nearby", 400);
    state_dialog_putline("screams. A merchant is being mugged by a bandit. You see some", 400);
    state_dialog_putline("dead guards on the ground. The bandit is seriously injured.", 400);
    state_dialog_putline("He must have killed the guards.", 400);
    state_dialog_waitkey();
    state_dialog_clear();
    state_dialog_putline("[BANDIT]: ''It's over! Surrender if you don't want to die!''", 400);
    state_dialog_putline("They don't notice your presence. The merchant refuses the offer", 400);
    state_dialog_putline("and grabs a little knife from one of the dead bodies.", 400);
    state_dialog_waitkey();
    state_dialog_clear();
    state_dialog_putline("[MERCHANT]: ''I... I am not scared of the likes of you...''", 400);
    state_dialog_putline("He is obviously lying. You could intervene and save his life.", 400);
    state_dialog_putline("You see a dagger on the ground, conveniently near.", 400);
    state_dialog_putline("Or you could let them kill each other and steal their goods...", 400);    
END

FUNCTION script_merchant_attack_text_ended()

BEGIN
    // TODO
END

FUNCTION script_merchant_attack_text_failed()

BEGIN
    // TODO
END

