package {{{mod_id}}}_mod.helpers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import {{{mod_id}}}_mod.characters.{{{character}}}Character;

import java.util.ArrayList;

public class ModHelper {

    public static final String mod_id = "{{{mod_id}}}_mod";
    public static final String res_id = "{{{mod_id}}}_res";

    public static String makeID(Class<?> cla) {
        return makeID(cla.getName());
    }

    public static String makeID(String name) {
        return mod_id + ":" + name;
    }

    public static String makeCardImgPath(AbstractCard.CardType type, Class<?> cla) {
        String type_name;
        switch (type) {
            case ATTACK:
                type_name = "attack";
                break;
            case POWER:
                type_name = "power";
                break;
            case STATUS:
            case CURSE:
            case SKILL:
                type_name = "skill";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
        String name = cla != null ? cla.getName() : "Beta";
        return res_id + "/img/cards/" + type_name + "/" + name + ".png";
    }

    public static String makeRelicImgPath(Class<?> cla) {
        String name = cla != null ? cla.getName() : "Beta";
        return res_id + "/img/relics/" + name + ".png";
    }

    public static void log(Object obj, String str) {
        Logger logger = LogManager.getLogger(obj.getClass());
        logger.info(str);
    }

    public static int cardRand(int size) {
        return AbstractDungeon.cardRng.random(size - 1);
    }

    public static ArrayList<AbstractMonster> getAliveMonsters() {
        ArrayList<AbstractMonster> ret_list = new ArrayList<>();
        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            if (!m.isDeadOrEscaped()) {
                ret_list.add(m);
            }
        }
        return ret_list;
    }

    // 获取除了指定怪物以外的怪物
    public static ArrayList<AbstractMonster> getAliveMonsters(AbstractMonster except) {
        ArrayList<AbstractMonster> ret_list = new ArrayList<>();
        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            if (!m.isDeadOrEscaped() && m != except) {
                ret_list.add(m);
            }
        }
        return ret_list;
    }

    public interface Lambda extends Runnable {
    }

    public static void addToBotAbstract(Lambda func) {
        AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
            @Override
            public void update() {
                func.run();
                isDone = true;
            }
        });
    }

    public static void addToTopAbstract(Lambda func) {
        AbstractDungeon.actionManager.addToTop(new AbstractGameAction() {
            @Override
            public void update() {
                func.run();
                isDone = true;
            }
        });
    }

    // 1号角色颜色
    public static AbstractCard.CardColor cardColor1() {
        return {{{character}}}Character.PlayerColorEnum.{{{character_up}}}_COLOR;
    }
}
