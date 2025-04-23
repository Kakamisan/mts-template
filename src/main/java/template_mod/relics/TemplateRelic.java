package {{{mod_id}}}_mod.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import {{{mod_id}}}_mod.helpers.ModHelper;

public class {{{character}}}Relic extends CustomRelic {

    // 需要配置
    private static final RelicTier RELIC_TIER = RelicTier.STARTER;  // 遗物类型

    // 一般不改
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;    // 点击音效
    private static final boolean BETA = false;  // 使用测试卡图

    // 不用改
    public static final String ID;
    private static final String IMG_PATH;

    // 不用改
    static {
        Class<?> cla = {{{character}}}Relic.class;
        ID = ModHelper.makeID(cla);
        IMG_PATH = ModHelper.makeRelicImgPath(BETA ? null : cla);
    }

    public {{{character}}}Relic() {
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
