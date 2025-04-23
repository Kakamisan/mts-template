package {{{mod_id}}}_mod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class CustomEasyCard extends CustomCard {

    private final CardStrings cardStrings;

    public CustomEasyCard(String id, String img, int cost, CardType type, CardColor color, CardRarity rarity, CardTarget target, CardStrings cardStrings) {
        super(id, cardStrings.NAME, img, cost, cardStrings.DESCRIPTION, type, color, rarity, target);
        this.cardStrings = cardStrings;
    }

    // 卡牌升级
    protected void up1() {}

    // 添加tag
    protected void addTag(CardTags tag) {
        this.tags.add(tag);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.up1();
            if (this.cardStrings.UPGRADE_DESCRIPTION != null) {
                this.rawDescription = this.cardStrings.UPGRADE_DESCRIPTION;
                this.initializeDescription();
            }
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {}
}
