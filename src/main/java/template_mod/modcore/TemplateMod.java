package {{{mod_id}}}_mod.modcore;

import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.abstracts.CustomRelic;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.*;
import {{{mod_id}}}_mod.cards.Strike;
import {{{mod_id}}}_mod.characters.{{{character}}}Character;
import {{{mod_id}}}_mod.helpers.ModHelper;
import {{{mod_id}}}_mod.relics.{{{character}}}Relic;

import java.nio.charset.StandardCharsets;

import static {{{mod_id}}}_mod.characters.{{{character}}}Character.PlayerColorEnum.{{{character_up}}}_COLOR;
import static {{{mod_id}}}_mod.helpers.ModHelper.mod_id;
import static {{{mod_id}}}_mod.helpers.ModHelper.res_id;
import static {{{mod_id}}}_mod.modcore.{{{character}}}Mod.MY_COLOR;

@SpireInitializer // 加载mod的注解
public class {{{character}}}Mod implements EditCardsSubscriber, EditStringsSubscriber, EditCharactersSubscriber, EditRelicsSubscriber, EditKeywordsSubscriber {

    // 角色颜色
    public static final Color MY_COLOR = new Color(249.0F / 255.0F, 210.0F / 255.0F, 43.0F / 255.0F, 1.0F);
    // 攻击牌的背景（小尺寸）
    public static final String BG_ATTACK_512 = res_id + "/img/512/bg_attack_512.png";
    // 技能牌的背景（小尺寸）
    public static final String BG_SKILL_512 = res_id + "/img/512/bg_skill_512.png";
    // 能力牌的背景（小尺寸）
    public static final String BG_POWER_512 = res_id + "/img/512/bg_power_512.png";
    // 小尺寸的能量图标（战斗中，牌堆预览）
    public static final String ENERGY_ORB = res_id + "/img/char/cost_orb.png";
    // 攻击牌的背景（大尺寸）
    public static final String BG_ATTACK_1024 = res_id + "/img/1024/bg_attack.png";
    // 技能牌的背景（大尺寸）
    public static final String BG_SKILL_1024 = res_id + "/img/1024/bg_skill.png";
    // 能力牌的背景（大尺寸）
    public static final String BG_POWER_1024 = res_id + "/img/1024/bg_power.png";
    // 在卡牌预览界面的能量图标
    public static final String BIG_ORB = res_id + "/img/char/card_orb.png";
    // 在卡牌和遗物描述中的能量图标
    public static final String SMALL_ORB = res_id + "/img/char/small_orb.png";

    // 构造方法
    public {{{character}}}Mod() {
        BaseMod.subscribe(this); // 告诉basemod你要订阅事件
        BaseMod.addColor(
                {{{character_up}}}_COLOR
                , MY_COLOR
                , MY_COLOR
                , MY_COLOR
                , MY_COLOR
                , MY_COLOR
                , MY_COLOR
                , MY_COLOR
                , BG_ATTACK_512
                , BG_SKILL_512
                , BG_POWER_512
                , ENERGY_ORB
                , BG_ATTACK_1024
                , BG_SKILL_1024
                , BG_POWER_1024
                , BIG_ORB
                , SMALL_ORB
        );
    }

    // 注解需要调用的方法，必须写
    public static void initialize() {
        new {{{character}}}Mod();
    }

    @Override
    public void receiveEditCards() {
        String lang;
        if (Settings.language == Settings.GameLanguage.ZHS) {
            lang = "ZHS";
        } else {
            lang = "ZHS";
        }
        BaseMod.loadCustomStringsFile(CardStrings.class, res_id + "/localization/" + lang + "/cards.json");
        new AutoAdd(mod_id).packageFilter(Strike.class).setDefaultSeen(true).cards();
    }

    // 当开始添加人物时，调用这个方法
    @Override
    public void receiveEditCharacters() {
        // 向basemod注册人物
        BaseMod.addCharacter(new {{{character}}}Character(CardCrawlGame.playerName), {{{character}}}Character.MY_CHARACTER_BUTTON
                , {{{character}}}Character.MY_CHARACTER_PORTRAIT, {{{character}}}Character.PlayerColorEnum.{{{character_up}}}_PLAYER);
    }

    public void receiveEditStrings() {
        String lang;
        if (Settings.language == Settings.GameLanguage.ZHS) {
            lang = "ZHS";
        } else {
            lang = "ZHS";
        }
        BaseMod.loadCustomStringsFile(CardStrings.class, res_id + "/localization/" + lang + "/cards.json");
        BaseMod.loadCustomStringsFile(CharacterStrings.class, res_id + "/localization/" + lang + "/characters.json");
        BaseMod.loadCustomStringsFile(RelicStrings.class, res_id + "/localization/" + lang + "/relics.json");
        BaseMod.loadCustomStringsFile(PowerStrings.class, res_id + "/localization/" + lang + "/powers.json");
    }

    @Override
    public void receiveEditRelics() {
//        BaseMod.addRelic(new SpecHat(), RelicType.SHARED); // RelicType表示是所有角色都能拿到的遗物，还是一个角色的独有遗物
        new AutoAdd(mod_id).packageFilter({{{character}}}Relic.class)
                .any(CustomRelic.class, (info, relic) -> {
                    BaseMod.addRelicToCustomPool(relic, {{{character}}}Character.PlayerColorEnum.{{{character_up}}}_COLOR);
                });
    }

    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String lang = "ZHS";
        if (Settings.language == Settings.GameLanguage.ZHS) {
            lang = "ZHS";
        }

        String json = Gdx.files.internal(res_id + "/localization/" + lang + "/keywords.json")
                .readString(String.valueOf(StandardCharsets.UTF_8));
        Keyword[] keywords = gson.fromJson(json, Keyword[].class);
        if (keywords != null) {
            for (Keyword keyword : keywords) {
                // 这个id要全小写
                BaseMod.addKeyword(mod_id, keyword.NAMES[0], keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }
}
