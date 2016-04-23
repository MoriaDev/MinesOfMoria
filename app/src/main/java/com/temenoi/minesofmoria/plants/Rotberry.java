package com.temenoi.minesofmoria.plants;

import com.temenoi.noosa.audio.Sample;
import com.temenoi.minesofmoria.Assets;
import com.temenoi.minesofmoria.Dungeon;
import com.temenoi.minesofmoria.actors.Char;
import com.temenoi.minesofmoria.actors.blobs.Blob;
import com.temenoi.minesofmoria.actors.blobs.ToxicGas;
import com.temenoi.minesofmoria.actors.buffs.Buff;
import com.temenoi.minesofmoria.actors.buffs.Roots;
import com.temenoi.minesofmoria.actors.mobs.Mob;
import com.temenoi.minesofmoria.effects.CellEmitter;
import com.temenoi.minesofmoria.effects.Speck;
import com.temenoi.minesofmoria.items.bags.Bag;
import com.temenoi.minesofmoria.items.potions.PotionOfStrength;
import com.temenoi.minesofmoria.scenes.GameScene;
import com.temenoi.minesofmoria.sprites.ItemSpriteSheet;
import com.temenoi.minesofmoria.utils.GLog;

public class Rotberry extends Plant {
	
	private static final String TXT_DESC = 
		"Berries of this shrub taste like sweet, sweet death.";
	
	{
		image = 7;
		plantName = "Rotberry";
	}
	
	@Override
	public void activate( Char ch ) {
		super.activate( ch );
		
		GameScene.add( Blob.seed( pos, 100, ToxicGas.class ) );
		
		Dungeon.level.drop( new Seed(), pos ).sprite.drop();
		
		if (ch != null) {
			Buff.prolong( ch, Roots.class, Roots.TICK * 3 );
		}
	}
	
	@Override
	public String desc() {
		return TXT_DESC;
	}
	
	public static class Seed extends Plant.Seed {
		{
			plantName = "Rotberry";
			
			name = "seed of " + plantName;
			image = ItemSpriteSheet.SEED_ROTBERRY;
			
			plantClass = Rotberry.class;
			alchemyClass = PotionOfStrength.class;
		}
		
		@Override
		public boolean collect( Bag container ) {
			if (super.collect( container )) {
				
				if (Dungeon.level != null) {
					for (Mob mob : Dungeon.level.mobs) {
						mob.beckon( Dungeon.hero.pos );
					}
					
					GLog.w( "The seed emits a roar that echoes throughout the dungeon!" );
					CellEmitter.center( Dungeon.hero.pos ).start( Speck.factory( Speck.SCREAM ), 0.3f, 3 );
					Sample.INSTANCE.play( Assets.SND_CHALLENGE );
				}
				
				return true;
			} else {
				return false;
			}
		}
		
		@Override
		public String desc() {
			return TXT_DESC;
		}
	}
}