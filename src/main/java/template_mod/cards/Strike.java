package {{{mod_id}}}_mod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import {{{mod_id}}}_mod.helpers.ModHelper;

public class Strike extends CustomEasyCard {

    // 每张卡都要改
    private static final int COST = 1;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    // 一般不改
    private static final AbstractCard.CardColor COLOR = ModHelper.cardColor1(); //
    private static final boolean BETA = false;  // 使用测试卡图

    // 不用改
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final String IMG_PATH;

    // 不用改
    static {
        Class<?> cla = Strike.class;
        ID = ModHelper.makeID(cla);
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
        IMG_PATH = ModHelper.makeCardImgPath(TYPE, BETA ? null : cla);
    }

    public Strike() {
        super(ID, IMG_PATH, COST, TYPE, COLOR, RARITY, TARGET, CARD_STRINGS);
        this.addTag(CardTags.STRIKE);
        this.addTag(CardTags.STARTER_STRIKE);
        this.baseDamage = 6;
    }

    @Override
    public void up1() {
        this.upgradeDamage(3);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AttackEffect.BLUNT_LIGHT));
    }
}
