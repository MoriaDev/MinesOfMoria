/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package com.temenoi.minesofmoria.actors.blobs;

import com.temenoi.minesofmoria.actors.Actor;
import com.temenoi.minesofmoria.actors.Char;
import com.temenoi.minesofmoria.actors.buffs.Buff;
import com.temenoi.minesofmoria.actors.buffs.Vertigo;
import com.temenoi.minesofmoria.effects.BlobEmitter;
import com.temenoi.minesofmoria.effects.Speck;

public class ConfusionGas extends Blob {
	
	@Override
	protected void evolve() {
		super.evolve();
		
		Char ch;
		for (int i=0; i < LENGTH; i++) {
			if (cur[i] > 0 && (ch = Actor.findChar( i )) != null) {
				Buff.prolong( ch, Vertigo.class, Vertigo.duration( ch ) );
			}
		}
	}
	
	@Override
	public void use( BlobEmitter emitter ) {
		super.use( emitter );
		
		emitter.pour( Speck.factory( Speck.CONFUSION, true ), 0.6f );
	}
	
	@Override
	public String tileDesc() {
		return "A cloud of confusion gas is swirling here.";
	}
}
