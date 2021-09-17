package com.beserrovsky.rgpotter.spells;

import com.beserrovsky.rgpotter.R;
import com.beserrovsky.rgpotter.SpellActivity;

public class LumusSpell extends Spell {

    public LumusSpell(SpellActivity ctx) {
        super(ctx);
        this.NAME = R.string.spell_lumus;
        this.DESC = R.string.spell_lumus_desc;
    }

    @Override
    public int Update(int a){
        return a + 1;
    }

    @Override
    public void OnSuccess() {

    }

    @Override
    public void OnFailure() {

    }
}
