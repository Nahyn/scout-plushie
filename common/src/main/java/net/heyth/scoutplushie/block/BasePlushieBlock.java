package net.heyth.scoutplushie.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class BasePlushieBlock extends HorizontalDirectionalBlock {
	public static final MapCodec<BasePlushieBlock> CODEC = simpleCodec(BasePlushieBlock::new);
	private static final VoxelShape SHAPE = Block.box(5.0, 0.0, 5.0, 11.0, 11.0, 11.0);

	public BasePlushieBlock(Properties properties) {
		super(properties);
	}

	protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}

	protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
		return CODEC;
	}

	public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
	}

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}
}
