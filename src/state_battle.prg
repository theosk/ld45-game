FUNCTION state_battle_start(string enemy_name, int enemy_power)

PRIVATE
    int result;
    string text;

BEGIN
    battle.enemy_name = enemy_name;
    battle.enemy_power = enemy_power;
    battle.enemy_health = 5;
    battle.player_health = 5;

    game.state = STATE_DIALOG_ID;
    frame(2000);

    loop
        state_battle_turn();
        frame(5000);
        
        if (battle.player_health == 0)
            // LOST
            state_dialog_putline("You just LOST the battle!!!", 1000);
            state_dialog_putline("You retreat. Better luck next time.", 1000);
            result = 0;
            break;
        end

        if (battle.enemy_health == 0)
            // WON
            state_dialog_putline("You just WON the battle!!!", 1000);
            result = 1;
            break;
        end
        frame;
    end

    state_dialog_waitkey();

    return(result);
END

FUNCTION state_battle_turn()

PRIVATE
    int roll;

BEGIN
    roll = rand(1, 6); // TODO: Add modifiers
    if (roll > battle.enemy_power)
        // Player hits
        state_battle_generateline("PLAYER", battle.enemy_name);
        battle.enemy_health--;
        
    else
        // Enemy hits
        state_battle_generateline(battle.enemy_name, "PLAYER");
        battle.player_health--;
    end
END


FUNCTION state_battle_generateline(string attacker_name, string target_name)

BEGIN
    x = rand(0, 10);
    switch (x)
        case 0:
            state_dialog_putline(attacker_name + " hits " + target_name);
            end

        case 1:
            state_dialog_putline(attacker_name + " slashes " + target_name);
            end

        case 2:
            state_dialog_putline(attacker_name + " overpowers" + target_name);
            end

        case 3:
            state_dialog_putline(attacker_name + " bites " + target_name);
            end

        case 4:
            state_dialog_putline(attacker_name + " kicks " + target_name + " in the butt");
            end

        case 5:
            state_dialog_putline(target_name + " trips and takes damage");
            end

        case 6:
            state_dialog_putline(attacker_name + " slaps " + target_name);
            end

        case 7:
            state_dialog_putline(target_name + " has digestive issues and takes damage");
            end

        case 8:
            state_dialog_putline(attacker_name + " shouts really hard and damages " + target_name);
            end

        case 9:
            state_dialog_putline(attacker_name + " sets a trap and " + target_name + " steps in it");
            end

        case 10:
            state_dialog_putline(attacker_name + " gives " + target_name + " some poisonous cake");
            end
        
    END
END