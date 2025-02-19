/*
 * Copyright (C) 2023 PixysOS
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.android.systemui.qs.tileimpl

import com.android.systemui.qs.tileimpl.QSTileImpl
import com.google.android.systemui.qs.tiles.BatterySaverTileGoogle
import com.google.android.systemui.qs.tiles.ReverseChargingTile
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey

@Module
interface GoogleQSModule {

  /** Inject ReverseChargingTile into tileMap in QSModule */
  @Binds
  @IntoMap
  @StringKey(ReverseChargingTile.TILE_SPEC)
  fun bindReverseChargingTile(reverseChargingTile: ReverseChargingTile): QSTileImpl<*>

  /** Inject BatterySaverTileGoogle into tileMap in QSModule */
  @Binds
  @IntoMap
  @StringKey(BatterySaverTileGoogle.TILE_SPEC)
  fun bindBatterySaverTileGoogle(batterySaverTileGoogle: BatterySaverTileGoogle): QSTileImpl<*>
}
