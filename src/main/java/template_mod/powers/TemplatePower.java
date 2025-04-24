package {{{mod_id}}}_mod.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import {{{mod_id}}}_mod.helpers.ModHelper;

public class {{{character}}}Power extends AbstractPower {

    // 一般不改
    private static final boolean BETA = false;  // 使用测试卡图

    // 不用改
    public static final String POWER_ID;
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;

    static {
        POWER_ID = ModHelper.makeID({{{character}}}Power.class);
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }

    public {{{character}}}Power(AbstractCreature owner, int Amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;

        // 如果需要不能叠加的能力，只需将上面的Amount参数删掉，并把下面的Amount改成-1就行
        this.amount = Amount;

//        // 添加一大一小两张能力图
//        String path128 = ModHelper.makePowerImgPath84(BETA ? "Beta" : "{{{character}}}Power");
//        String path48 = ModHelper.makePowerImgPath32(BETA ? "Beta" : "{{{character}}}Power");
//        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
//        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);

        // 使用官方能力图 和上面2选1
        this.loadRegion("loop");

        // 首次添加能力更新描述
        this.updateDescription();
    }

    // 能力在更新时如何修改描述
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

}
