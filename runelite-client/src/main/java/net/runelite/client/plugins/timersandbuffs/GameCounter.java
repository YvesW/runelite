/*
 * Copyright (c) 2024, YvesW <https://github.com/YvesW>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.runelite.client.plugins.timersandbuffs;

import java.awt.Color;
import java.util.function.Predicate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.runelite.api.ItemID;
import net.runelite.api.SpriteID;

@Getter(AccessLevel.PACKAGE)
@AllArgsConstructor
enum GameCounter
{
	COLOSSEUM_DOOM(SpriteID.COLOSSEUM_DOOM, GameTimerImageType.SPRITE, "Doom"),
	CURSE_OF_THE_MOONS_BLUE(ItemID.BLUE_MOON_HELM, GameTimerImageType.ITEM, "Curse of the Moons (Blue Moon)", ColorBoundaryType.GREATER_THAN_EQUAL_TO, Color.RED, 18),
	CURSE_OF_THE_MOONS_ECLIPSE(ItemID.ECLIPSE_MOON_HELM, GameTimerImageType.ITEM, "Curse of the Moons (Eclipse Moon)"),
	;

	private final int imageId;
	private final GameTimerImageType imageType;
	private final String description;
	private final ColorBoundaryType colorBoundaryType;
	private final Color color;
	private final int boundary;

	GameCounter(int imageId, GameTimerImageType idType, String description)
	{
		this(imageId, idType, description, ColorBoundaryType.NO_BOUNDARY, Color.WHITE, 0);
	}

	@AllArgsConstructor
	enum ColorBoundaryType
	{
		GREATER_THAN_EQUAL_TO(buffCounter -> buffCounter.getCount() >= buffCounter.getGameCounter().getBoundary()),
		LESS_THAN_EQUAL_TO(buffCounter -> buffCounter.getCount() <= buffCounter.getGameCounter().getBoundary()),
		NO_BOUNDARY(buffCounter -> false);

		final Predicate<BuffCounter> shouldRecolor;
	}
}
