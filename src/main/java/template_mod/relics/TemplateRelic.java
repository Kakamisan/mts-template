package template_mod.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import template_mod.helpers.ModHelper;

public class TemplateRelic extends CustomRelic {

    // 需要配置
    private static final RelicTier RELIC_TIER = RelicTier.STARTER;  // 遗物类型

    // 一般不改
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;    // 点击音效

    // 不用改
    public static final String ID;
    private static final String IMG_PATH;

    // 不用改
    static {
        Class<?> cla = TemplateRelic.class;
        ID = ModHelper.makeID(cla);
        IMG_PATH = ModHelper.makeRelicImgPath(cla);
    }

    public TemplateRelic() {
        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
    }

    @Override
    // 获取遗物描述，但原版游戏只在初始化和获取遗物时调用，故该方法等于初始描述
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public void atBattleStart() {
        super.atBattleStart();
        this.flash();
    }
}
