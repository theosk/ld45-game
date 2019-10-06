CONST
    STATUS_START = 0;
    STATUS_ENDED = 2000;
    STATUS_FAILED = 9999;

    STATE_INTRO_ID = 0;
    STATE_DIALOG_ID = 1;
    STATE_BATTLE_ID = 2;
    STATE_MAP_ID = 3;

    LOCATION_NONE = -1;
    LOCATION_SHORE = 0;
    LOCATION_WOODS = 1;
    LOCATION_TOWN = 2;
    LOCATION_BANDIT_CAMP = 3;
    LOCATION_BANDIT_BOSS = 4;
    LOCATION_ORC_SETTLEMENT = 5;
    LOCATION_ORC_TREASURE_ROOM = 6;
    LOCATION_DEMON_FIELDS = 7;
    LOCATION_DEMON_LAIR = 8;
    LOCATION_INN = 9;
    LOCATION_JEWEL_SHOP = 10;
    LOCATION_WEAPON_SHOP = 11;
    LOCATION_MOUNTAIN = 12;
    LOCATION_CASTLE = 13;
    

    // MAIN quests
    STATUS_SHORE_VISITED = 1;
    STATUS_WOODS_VISITED = 2;
    STATUS_TOWN_VISITED = 3;
    STATUS_INN_VISITED = 4;
    STATUS_BANDITS_DEFEATED = 5;
    STATUS_FRAGMENTS_SEARCH = 6;
    STATUS_DEMON_KING = 7;
    STATUS_DEMON_DEFEATED = 8;
GLOBAL

struct game;
    int state = STATE_INTRO_ID;
    int font;
end

struct quests;
    int main = STATUS_START;
    int saveMerchant = STATUS_START;

    int killed_bandits = 0;
    int killed_orcs = 0;
    int killed_demons = 0;
end

struct inventory;
    int gold = 0;
    int dagger = 0; // 1d6 attack
    int sword = 0; // + 1d4 attack for offhand weapon;
    int amulet = 0; // +1 attack
    int weddingRing = 0; // +1 attack
    int shield = 0; // + 1 attack for some reason
    int treasureMap = 0;
    int mountainFragment = 0;
    int shoreFragment = 0;
    int forestFragment = 0;
end

struct battle;
    string enemy_name;
    int enemy_power;
    int enemy_health;
    int player_health;
end

struct dialog;
    int currentLine;
    string line_text[10];
    int line_id[10];

    text_offset_x = 24;
    text_offset_y = 22;
    text_vertical_space = 38;

    int answered = 0;
    int chosen_answer = 0;
    
end

struct map;
    int ui_id;
    string text_location = "";
    string text_inventory = "";
    int text_reset_count = 60*2;
    int area_unlocked[999];
    int next_destination = LOCATION_NONE;
end

struct shops
    price_sword = 100;
    price_ring = 500;
end


int img_bg;
int img_bg_map;
int img_bg_dialog;
int img_bg_intro;
int img_bg_bandit_intro;
int img_logo;
int img_marker_unknown;
int img_marker[999];
int img_marker_frame;

struct snd;
    int hit[7];
    int win;
    int coin;
    int select;
    int ask;
end
