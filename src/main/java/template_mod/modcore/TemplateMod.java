package template_mod.modcore;

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
import template_mod.cards.Strike;
import template_mod.characters.TemplateCharacter;
import template_mod.helpers.ModHelper;
import template_mod.relics.TemplateRelic;

import java.nio.charset.StandardCharsets;

import static template_mod.helpers.ModHelper.mod_id;
import static template_mod.helpers.ModHelper.res_id;

@SpireInitializer // 加载mod的注解
public class TemplateMod implements EditCardsSubscriber, EditStringsSubscriber, EditCharactersSubscriber, EditRelicsSubscriber, EditKeywordsSubscriber {

    // 构造方法
    public TemplateMod() {
        BaseMod.subscribe(this); // 告诉basemod你要订阅事件
        BaseMod.addColor(
                ModHelper.cardColor1()
                , TemplateCharacter.MY_COLOR
                , TemplateCharacter.MY_COLOR
                , TemplateCharacter.MY_COLOR
                , TemplateCharacter.MY_COLOR
                , TemplateCharacter.MY_COLOR
                , TemplateCharacter.MY_COLOR
                , TemplateCharacter.MY_COLOR
                , TemplateCharacter.BG_ATTACK_512
                , TemplateCharacter.BG_SKILL_512
                , TemplateCharacter.BG_POWER_512
                , TemplateCharacter.ENERGY_ORB
                , TemplateCharacter.BG_ATTACK_1024
                , TemplateCharacter.BG_SKILL_1024
                , TemplateCharacter.BG_POWER_1024
                , TemplateCharacter.BIG_ORB
                , TemplateCharacter.SMALL_ORB
        );
    }

    // 注解需要调用的方法，必须写
    public static void initialize() {
        new TemplateMod();
    }

    @Override
    public void receiveEditCards() {
        String lang;
        if (Settings.language == Settings.GameLanguage.ZHS) {
            lang = "zhs";
        } else {
            lang = "zhs";
        }
        BaseMod.loadCustomStringsFile(CardStrings.class, res_id + "/localization/" + lang + "/cards.json");
        new AutoAdd(mod_id).packageFilter(Strike.class).setDefaultSeen(true).cards();
    }

    // 当开始添加人物时，调用这个方法
    @Override
    public void receiveEditCharacters() {
        // 向basemod注册人物
        BaseMod.addCharacter(new TemplateCharacter(CardCrawlGame.playerName), TemplateCharacter.MY_CHARACTER_BUTTON
                , TemplateCharacter.MY_CHARACTER_PORTRAIT, TemplateCharacter.PlayerColorEnum.TEMPLATE_PLAYER);
    }

    public void receiveEditStrings() {
        String lang;
        if (Settings.language == Settings.GameLanguage.ZHS) {
            lang = "zhs";
        } else {
            lang = "zhs";
        }
        BaseMod.loadCustomStringsFile(CardStrings.class, res_id + "/localization/" + lang + "/cards.json");
        BaseMod.loadCustomStringsFile(CharacterStrings.class, res_id + "/localization/" + lang + "/characters.json");
        BaseMod.loadCustomStringsFile(RelicStrings.class, res_id + "/localization/" + lang + "/relics.json");
        BaseMod.loadCustomStringsFile(PowerStrings.class, res_id + "/localization/" + lang + "/powers.json");
    }

    @Override
    public void receiveEditRelics() {
//        BaseMod.addRelic(new SpecHat(), RelicType.SHARED); // RelicType表示是所有角色都能拿到的遗物，还是一个角色的独有遗物
        new AutoAdd(mod_id).packageFilter(TemplateRelic.class)
                .any(CustomRelic.class, (info, relic) -> {
                    BaseMod.addRelicToCustomPool(relic, TemplateCharacter.PlayerColorEnum.TEMPLATE_COLOR);
                });
    }

    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String lang = "zhs";
        if (Settings.language == Settings.GameLanguage.ZHS) {
            lang = "zhs";
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
