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
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

public class LightPlushieBlock extends BasePlushieBlock {
	public static final BooleanProperty LIT = BlockStateProperties.LIT;

	public LightPlushieBlock(Properties properties) {
		super(properties
			.lightLevel(
				blockstate -> blockstate.getValue(BlockStateProperties.LIT) ? 15 : 0
			)
		);
		this.registerDefaultState(
			this.stateDefinition
				.any()
				.setValue(WATERLOGGED, Boolean.valueOf(false))
				.setValue(FACING, Direction.NORTH)
				.setValue(LIT, Boolean.valueOf(false))
		);
	}

	public void toggle_light(BlockState state, ServerLevel level, BlockPos pos, Player player) {
		BlockState blockstate = state.cycle(LIT);
		Constants.LOG.info("toggle_light to {}", blockstate.getValue(LIT));

		level.playSound(
			null,
			pos,
			blockstate.getValue(LIT) ? SoundEvents.WOODEN_BUTTON_CLICK_ON : SoundEvents.WOODEN_BUTTON_CLICK_OFF,
			SoundSource.BLOCKS
		);

		level.setBlock(pos, blockstate, 3);
		level.gameEvent(player, GameEvent.BLOCK_ACTIVATE, pos);
	}

	@Override
	protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
		if (level instanceof ServerLevel serverlevel) {
			toggle_light(state, serverlevel, pos, player);
		}
		return InteractionResult.SUCCESS;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, WATERLOGGED, LIT);
	}
}
