package net.heyth.scoutplushie.block;

import com.mojang.serialization.MapCodec;
import net.heyth.scoutplushie.Constants;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class BasePlushieBlock extends HorizontalDirectionalBlock implements SimpleWaterloggedBlock {
	public static final MapCodec<BasePlushieBlock> CODEC = simpleCodec(BasePlushieBlock::new);
	private static final VoxelShape SHAPE = Block.box(5.0, 0.0, 5.0, 11.0, 11.0, 11.0);

	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	public BasePlushieBlock(Properties properties) {
		super(properties
			.noOcclusion()
			.destroyTime(0.2f)
			.explosionResistance(256.0f)
			.sound(SoundType.WOOL)
		);

		this.registerDefaultState(
			this.stateDefinition
				.any()
				.setValue(WATERLOGGED, Boolean.valueOf(false))
				.setValue(FACING, Direction.NORTH)
		);
	}

	protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}

	protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
		return CODEC;
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		LevelAccessor levelaccessor = context.getLevel();
		BlockPos blockpos = context.getClickedPos();
		boolean flag = levelaccessor.getFluidState(blockpos).getType() == Fluids.WATER;
		return this.defaultBlockState()
			.setValue(WATERLOGGED, Boolean.valueOf(flag))
			.setValue(FACING, context.getHorizontalDirection().getOpposite());
	}

	@Override
	protected BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
		if (state.getValue(WATERLOGGED)) {
			level.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
		}

		return super.updateShape(state, facing, facingState, level, currentPos, facingPos);
	}

	@Override
	protected FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, WATERLOGGED);
	}
}
