package com.example.textgame;

public class Events {
    public Events() {

    }

    public String kill, cube, goldd, theft, trade, ch, easy;

    public String rules = ("\n-- Теперь тебе нужно выбрать персонажа (класс, hp, lvl, gold):\n\n" +
            "-- Ураг гро-Шуб (маг, 5, 3, 50) --\n" +
            "Ураг гро-Шуб — орк-маг, что редкость для орков.\n" +
            "Ураг является библиотекарем Арканеума.\n" +
            "Коллекционирует редкие книги и торгует менее ценными.\n\n" +
            "-- Синмир (воин, 12, 1, 10) --\n" +
            "Синмир - норд и постоянный жилец в \"Гарцующей кобыле\".\n" +
            "Он часто комментирует отсутствие безопасности в Вайтране.\n\n" +
            "-- Карлия (вор, 7, 1, 100) --\n" +
            "Карлия — данмерка, член Гильдии воров.\n" +
            "Изгнана из организации после обвинения в убийстве Галла Дезидения.\n\n\n");

    public String good = ("Не торопись...\n\n\"ИСТИННЫЕ ВОЙНЫ НИКОГДА НЕ ПРОИГРЫВАЮТ.\n" +
            "ТЫ ВЫИГРАЛ! Мои поздравления! Ты прошел этот непростой квест!");

    public String bad = ("Не торопись...\n\nДОСТОЙНЫЕ ВОЙНЫ УМИРАЮТ ДОСТОЙНО...\n" +
            "ТЫ ПРОИГРАЛ. Но не стоит отчаиваться! Ты можешь попробовать еще раз ;)");

    public String wizard = ("-- Со стороны библиотеки раздался звон металла и крик: \"За орду!!!\" Что бы это могло значить?..\n\n\n");

    public String warrior = ("-- \"Охрана в Вайтране ужасна. Просто курам на смех.\" - сказал Синмир, допивая очередную пинту эля.\n\n\n");

    public String thief = ("-- \"Мы связаны с нею узами делового соглашения...\" Что тут сказать... Соловьи, они такие.\n\n\n");

    public String nothing = ("-- Сегодня не произошло ничего интересного.\nЭльфы все также борятся за права деревьев.\n\n\n");

    public String hard = ("-- Ты встретил варга, но он довольно силен...\n" +
            "Брось D6, чтобы узнать итоги боя.\n\n\n");

    public String begin = ("\n-- Цель игры - достигнуть 30 уровня и не быть убитым)\n\n" +
            "-- Правила игры очень просты:\n\n" +
            "   Для определения развития событий тебе нужно кинуть два кубика D6.\n\n" +
            "   Кнопки - это и есть твои кубики. Нажми на них, чтобы определить результат броска.\n\n" +
            "-- Для того, чтобы узнать характеристики своего персонажа, во время игры нажми на кнопку \"ГЕРОЙ\".\n\n" +
            "-- Чтобы начать игру нажми \"ИГРАТЬ\".\n\n" +
            "-- Результаты других игроков можно узнать в разделе \"РЕКОРДЫ\".\n\n" +
            "-- Для выхода из игры нажми \"ВЫХОД\".");

    void Unit(int hp, int lvl, int gold) {
        ch = ("-- Твои характеристики:\n" +
                "Жизни - " + hp + ".\n" +
                "Уровень - " + lvl + ".\n" +
                "Золото - " + gold + "\n\n\n");
    }

    void Easy(int gold) {
        easy = ("-- Ты встретил врага, но тебе повезло! Убить его было довольно просто. Тебе удалось собрать с трупа немного золота.\n\n" +
                "ПОЛУЧЕН 1 УРОВЕНЬ И НАЙДЕНО " + gold + " ЗОЛОТА.\n\n\n");
    }

    void kill(int hp) {
        kill = ("-- Во время броска тебя поймал Карпов. \"А ты знал, что азартные игры запрещены на территории универа? А ну ка дай сюда студак...\"\n\n" +
                "Ты так и не сумел стащить студак у охранника.\nУ ТЕБЯ ОСТАЛОСЬ " + hp + " hp.\n\n\n");
    }

    void d6(int d6, int gold) {
        if (d6 % 2 == 0) {
            cube = ("-- Бой был нелегкий, но тебе повезло. Ты смог повергнуть варга. \"Да, этот бой я запомню надолго...\"\n\nПОТЕРЯНА 1 ЖИЗНЬ. ПОЛУЧЕНО 2 УРОВНЯ. НАЙДЕНО " + gold + " ЗОЛОТА.\n\n\n");
        } else {
            cube = ("-- Варг оказался слишком сильным. Ты с трудом сумел сбежать с поля боя. \"Лучше бы я не вылезал сегодня из постели...\" - подумал ты.\n\nПОТЕРЯНО 2 ЖИЗНИ. ПОЛУЧЕНО 3 УРОВНЯ.\n\n\n");
        }
    }

    void gold(int gold) {
        goldd = ("-- Мои поздравления! Ты нашел сокровища! Быстрее забирай их, пока тебя не ограбили...\n\nНАЙДЕНО " + gold + " ЗОЛОТА.\n\n\n");
    }

    void theft(int gold, int hp) {
        theft = ("-- Ты наткнулся на королевских дезертиров. Они слишком сильны. Но ты можешь откупиться от них 30 золотыми.\n\n");
        if (gold < 30) {
            theft += ("\"Постой-ка... Я смотрю, твои карманы пустые. Делать нечего, снимай штаны...\"\n\nДЕЗЕРТИРЫ ЗАБРАЛИ ТВОИ ШТАНЫ. ");
            if (hp < 3) {
                theft += ("СЛЕДУЮЩЕЙ НОЧЬЮ ТЫ ЗАМЕРЗ И УМЕР.\n\n\n");
            } else {
                theft += ("СЛЕДУЮЩЕЙ НОЧЬЮ ТЫ ЗАМЕРЗ.\nУ ТЕБЯ ОСТАЛОСЬ " + (hp - 2) + " hp.\n\n\n");
            }
        } else {
            theft += ("\"Похоже, у тебя нет выбора\".\n\nПОТЕРЯНО 30 ЗОЛОТА.\n\n\n");
        }
    }

    void Trade(int gold, int d6) {
        if (d6 == 1 && gold >= 50) {
            trade = ("ПОЛУЧЕНА 1 ЖИЗНЬ.\n\n\n");
        } else if (d6 == 2 && gold >= 100) {
            trade = ("ПОЛУЧЕН 1 УРОВЕНЬ.\n\n\n");
        } else {
            trade = ("НУЖНО БОЛЬШЕ ЗОЛОТА...\n\n\n");
        }
    }
}
