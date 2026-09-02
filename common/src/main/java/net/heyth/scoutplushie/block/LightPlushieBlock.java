package net.heyth.scoutplushie.block;

import net.heyth.scoutplushie.Constants;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

public class LightPlushieBlock extends BasePlushieBlock {
	public static final IntegerProperty LEVEL = BlockStateProperties.LEVEL;

	public LightPlushieBlock(Properties properties) {
		super(properties
			.lightLevel(
				blockstate -> blockstate.getValue(BlockStateProperties.LEVEL)
			)
		);
		this.registerDefaultState(
			this.stateDefinition
				.any()
				.setValue(WATERLOGGED, Boolean.valueOf(false))
				.setValue(FACING, Direction.NORTH)
				.setValue(LEVEL, Integer.valueOf(0))
		);
	}

	private int getUpdatedLightLevel(BlockState state, Player player) {
		int currentValue = state.getValue(LEVEL);

		currentValue = player.isCrouching() ? (currentValue-3) : (currentValue +3);

		if(currentValue < 0) {
			currentValue = 15;
		} else if (currentValue > 15) {
			currentValue = 0;
		}

		return currentValue;
	}

	@Override
	protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
		if (level instanceof ServerLevel serverlevel) {
			BlockState blockstate = state.setValue(LEVEL, getUpdatedLightLevel(state, player));

			level.playSound(
				null,
				pos,
				player.isCrouching() ? SoundEvents.WOODEN_BUTTON_CLICK_OFF : SoundEvents.WOODEN_BUTTON_CLICK_ON,
				SoundSource.BLOCKS
			);

			level.setBlock(pos, blockstate, 3);
			level.gameEvent(player, GameEvent.BLOCK_ACTIVATE, pos);
		}
		return InteractionResult.SUCCESS;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, WATERLOGGED, LEVEL);
	}
}
