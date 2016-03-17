package co.uk.silvania.roads.client.render;

import co.uk.silvania.roads.blocks.NonRoadBlock;
import co.uk.silvania.roads.client.ClientProxy;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class NonRoadBlockRenderingHandler implements ISimpleBlockRenderingHandler {
	Tessellator tess;
	
	@Override
	public void renderInventoryBlock(Block block, int meta, int modelId, RenderBlocks renderer) {
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		renderer.blockAccess.getHeight();
        tess = Tessellator.instance;
        tess.setBrightness(block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z));
        
        float f = 0.8F;
        int c = block.colorMultiplier(renderer.blockAccess, x, y, z);
        float f1 = (float)(c >> 16 & 255) / 255.0F;
        float f2 = (float)(c >> 8 & 255) / 255.0F;
        float f3 = (float)(c & 255) / 255.0F;

        tess.setColorOpaque_F(f * f1, f * f2, f * f3);
        IIcon icon;
        IIcon icon1;

        //int meta = renderer.blockAccess.getBlockMetadata(x, y, z);
        icon = block.getIcon(renderer.blockAccess, x, y, z, 1);
        icon1 = block.getIcon(renderer.blockAccess, x, y, z, 0);

        double u0 = (double)icon.getMinU();
        double u1 = (double)icon.getMaxU();
        
        double v0 = (double)icon.getMinV();
        double v1 = (double)icon.getMaxV();
        
        double u0_2 = (double)icon1.getMinU();
        double u1_2 = (double)icon1.getMaxU();
        
        double v0_2 = (double)icon1.getMinV();
        double v1_2 = (double)icon1.getMaxV();
        
        //Initial height values. Quad Height method simply gets the height from meta via quick calculation (more reliable than checking bounding box size, for some reason)
        //Value is compass.
        double a  = quadHeight(world.getBlockMetadata(x, y, z)); //Current block
        double n  = quadHeight(world.getBlockMetadata(x,   y, z-1));
        double ne = quadHeight(world.getBlockMetadata(x+1, y, z-1));
        double e  = quadHeight(world.getBlockMetadata(x+1, y, z  ));
        double se = quadHeight(world.getBlockMetadata(x+1, y, z+1));
        double s  = quadHeight(world.getBlockMetadata(x,   y, z+1));
        double sw = quadHeight(world.getBlockMetadata(x-1, y, z+1));
        double w  = quadHeight(world.getBlockMetadata(x-1, y, z  ));
        double nw = quadHeight(world.getBlockMetadata(x-1, y, z-1));

        //If the block is max height, it should check the blocks ABOVE to continue a ramp to the next level. As blocks always go up, it makes
        //no sense to check things on it's own level, because it'll never go up there.
        double nt  = 0;
        double net = 0;
        double et  = 0;
        double set = 0;
        double st  = 0;
        double swt = 0;
        double wt  = 0;
        double nwt = 0;
        
        double offset = 1-quadHeight(world.getBlockMetadata(x, y, z));
        //This currently only checks the block next to it, and if there's a block there then it adds full.
        //should check block ABOVE the block next to it, and add the correct amount.
        //double not boolean, y+1, meta to get value. boom.
        if (world.getBlock(x,   y+1, z-1) instanceof NonRoadBlock) { nt  = quadHeight(world.getBlockMetadata(x,   y+1, z-1)) + offset;}
        if (world.getBlock(x+1, y+1, z-1) instanceof NonRoadBlock) { net = quadHeight(world.getBlockMetadata(x+1, y+1, z-1)) + offset;}
        if (world.getBlock(x+1, y+1, z)   instanceof NonRoadBlock) { et  = quadHeight(world.getBlockMetadata(x+1, y+1, z  )) + offset;}
        if (world.getBlock(x+1, y+1, z+1) instanceof NonRoadBlock) { set = quadHeight(world.getBlockMetadata(x+1, y+1, z+1)) + offset;}
        if (world.getBlock(x,   y+1, z+1) instanceof NonRoadBlock) { st  = quadHeight(world.getBlockMetadata(x,   y+1, z+1)) + offset;}
        if (world.getBlock(x-1, y+1, z+1) instanceof NonRoadBlock) { swt = quadHeight(world.getBlockMetadata(x-1, y+1, z+1)) + offset;}
        if (world.getBlock(x-1, y+1, z)   instanceof NonRoadBlock) { wt  = quadHeight(world.getBlockMetadata(x-1, y+1, z  )) + offset;}
        if (world.getBlock(x-1, y+1, z-1) instanceof NonRoadBlock) { nwt = quadHeight(world.getBlockMetadata(x-1, y+1, z-1)) + offset;}
                
        double nQh = 0;
        double neQh = 0;
        double eQh = 0;
        double seQh = 0;
        double sQh = 0;
        double swQh = 0;
        double wQh = 0;
        double nwQh = 0;
        
        if (world.getBlock(x,   y, z-1) instanceof NonRoadBlock) { nQh  = quadHeight(world.getBlockMetadata(x,   y, z-1)); } else if (!(world.isAirBlock(x,   y, z-1))) { nQh  = 1;}
        if (world.getBlock(x+1, y, z-1) instanceof NonRoadBlock) { neQh = quadHeight(world.getBlockMetadata(x+1, y, z-1)); } else if (!(world.isAirBlock(x+1, y, z-1))) { neQh = 1;}
        if (world.getBlock(x+1, y, z)   instanceof NonRoadBlock) { eQh  = quadHeight(world.getBlockMetadata(x+1, y, z));   } else if (!(world.isAirBlock(x+1, y, z)))   { eQh  = 1;}
        if (world.getBlock(x+1, y, z+1) instanceof NonRoadBlock) { seQh = quadHeight(world.getBlockMetadata(x+1, y, z+1)); } else if (!(world.isAirBlock(x+1, y, z+1))) { seQh = 1;}
        if (world.getBlock(x,   y, z+1) instanceof NonRoadBlock) { sQh  = quadHeight(world.getBlockMetadata(x,   y, z+1)); } else if (!(world.isAirBlock(x,   y, z+1))) { sQh  = 1;}
        if (world.getBlock(x-1, y, z+1) instanceof NonRoadBlock) { swQh = quadHeight(world.getBlockMetadata(x-1, y, z+1)); } else if (!(world.isAirBlock(x-1, y, z+1))) { swQh = 1;}
        if (world.getBlock(x-1, y, z)   instanceof NonRoadBlock) { wQh  = quadHeight(world.getBlockMetadata(x-1, y, z));   } else if (!(world.isAirBlock(x-1, y, z)))   { wQh  = 1;}
        if (world.getBlock(x-1, y, z-1) instanceof NonRoadBlock) { nwQh = quadHeight(world.getBlockMetadata(x-1, y, z-1)); } else if (!(world.isAirBlock(x-1, y, z-1))) { nwQh = 1;}
        
        if ((world.getBlock(x,   y, z-1) instanceof NonRoadBlock) || (nt > 0))  { n  = nQh  + nt;}  else { n  = a;} //TODO this one!
        if ((world.getBlock(x+1, y, z-1) instanceof NonRoadBlock) || (net > 0)) { ne = neQh + net;} else { ne = a;}
        if ((world.getBlock(x+1, y, z)   instanceof NonRoadBlock) || (et > 0))  { e  = eQh  + et;}  else { e  = a;}
        if ((world.getBlock(x+1, y, z+1) instanceof NonRoadBlock) || (set > 0)) { se = seQh + set;} else { se = a;}
        if ((world.getBlock(x,   y, z+1) instanceof NonRoadBlock) || (st > 0))  { s  = sQh  + st;}  else { s  = a;}
        if ((world.getBlock(x-1, y, z+1) instanceof NonRoadBlock) || (swt > 0)) { sw = swQh + swt;} else { sw = a;}
        if ((world.getBlock(x-1, y, z)   instanceof NonRoadBlock) || (wt > 0))  { w  = wQh  + wt;}  else { w  = a;}
        if ((world.getBlock(x-1, y, z-1) instanceof NonRoadBlock) || (nwt > 0)) { nw = nwQh + nwt;} else { nw = a;}
        
        //Create a boolean as to whether there's a valid connection on each of the 8 sides.
        //Check if each side is HIGHER than the current block. We only go up, never down.
        boolean nB  = n  > a;
        boolean neB = ne > a;
        boolean eB  = e  > a;
        boolean seB = se > a;
        boolean sB  = s  > a;
        boolean swB = sw > a;
        boolean wB  = w  > a;
        boolean nwB = nw > a;

        double neQ = 0;
        double seQ = 0;
        double nwQ = 0;
        double swQ = 0;
        
        //We prioritise corners. If a corner isn't valid, assume north/south first else east/west (It can only be one or the other if corner is invalid)
        //If no matches, then set to own height (Do that first really)
        if (!nB && !nwB && !neB) { n = a;}
        if (!eB && !neB && !seB) { e = a;}
        if (!sB && !swB && !seB) { s = a;}
        if (!wB && !nwB && !swB) { w = a;}
        
        if (nB && eB) { neQ = ne; } else if (nB) { neQ = n;} else if (eB) { neQ = e;} else if (neB) { neQ = ne; } else { neQ = a;}
        if (nB && wB) { nwQ = nw; } else if (nB) { nwQ = n;} else if (wB) { nwQ = w;} else if (nwB) { nwQ = nw; } else { nwQ = a;}
        if (sB && eB) { seQ = se; } else if (sB) { seQ = s;} else if (eB) { seQ = e;} else if (seB) { seQ = se; } else { seQ = a;}
        if (sB && wB) { swQ = sw; } else if (sB) { swQ = s;} else if (wB) { swQ = w;} else if (swB) { swQ = sw; } else { swQ = a;}
        
        //Change direct compass to match a corner if no direct line is found. Useful for side height calculations.
        /*if (!nB && nwB) { n = nw;}
        if (!nB && neB) { n = ne;}
        if (!eB && neB) { e = ne;}
        if (!eB && seB) { e = se;}
        if (!sB && swB) { s = sw;}
        if (!sB && seB) { s = se;}
        if (!wB && neB) { w = ne;}
        if (!wB && seB) { w = se;}*/
        
        //If all four sides are equal (same height) colour should be flat. Else, slightly darker.
        int col = 255;
        if (!(neQ == nwQ && neQ == seQ && neQ == swQ)) {
        	col = 240;
        }

        //System.out.println("SWB: " + swB + ", SB: " + sB + ", WB: " + wB);
        //System.out.println("NE: " + neQ + ", SE: " + seQ + ", SW: " + swQ + ", NW: " + nwQ);
        
        //Now, we actually render each face.
        //Each face needs the colour setting, and then four vertex.
        //Colour is required as it's reduced for sides and bottom, to give a false effect of "shading" which is surprisingly very important.
        
        /*
         *  AO code shamelessly taken directly from RenderBlocks with a few cleanups.
         */
    	renderer.enableAO = true;
        boolean flag = false;
        float f3_2 = 0.0F;
        float f4 = 0.0F;
        float f5 = 0.0F;
        float f6 = 0.0F;
        boolean flag1 = true;
    	
    	boolean flag2;
        boolean flag3;
        boolean flag4;
        boolean flag5;
        int i1;
        float f7;
        
        int l = block.getMixedBrightnessForBlock(world, x, y, z);
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(983055);

        renderer.aoBrightnessXYNP = block.getMixedBrightnessForBlock(world, x - 1, y, z);
        renderer.aoBrightnessXYPP = block.getMixedBrightnessForBlock(world, x + 1, y, z);
        renderer.aoBrightnessYZPN = block.getMixedBrightnessForBlock(world, x, y, z - 1);
        renderer.aoBrightnessYZPP = block.getMixedBrightnessForBlock(world, x, y, z + 1);
        renderer.aoLightValueScratchXYNP = world.getBlock(x - 1, y, z).getAmbientOcclusionLightValue();
        renderer.aoLightValueScratchXYPP = world.getBlock(x + 1, y, z).getAmbientOcclusionLightValue();
        renderer.aoLightValueScratchYZPN = world.getBlock(x, y, z - 1).getAmbientOcclusionLightValue();
        renderer.aoLightValueScratchYZPP = world.getBlock(x, y, z + 1).getAmbientOcclusionLightValue();
        flag2 = world.getBlock(x + 1, y + 1, z).getCanBlockGrass();
        flag3 = world.getBlock(x - 1, y + 1, z).getCanBlockGrass();
        flag4 = world.getBlock(x, y + 1, z + 1).getCanBlockGrass();
        flag5 = world.getBlock(x, y + 1, z - 1).getCanBlockGrass();

        if (!flag5 && !flag3) {
        	renderer.aoLightValueScratchXYZNPN = renderer.aoLightValueScratchXYNP;
        	renderer.aoBrightnessXYZNPN = renderer.aoBrightnessXYNP;
        } else {
        	renderer.aoLightValueScratchXYZNPN = world.getBlock(x - 1, y, z - 1).getAmbientOcclusionLightValue();
        	renderer.aoBrightnessXYZNPN = block.getMixedBrightnessForBlock(world, x - 1, y, z - 1);
        }

        if (!flag5 && !flag2) {
        	renderer.aoLightValueScratchXYZPPN = renderer.aoLightValueScratchXYPP;
        	renderer.aoBrightnessXYZPPN = renderer.aoBrightnessXYPP;
        } else {
        	renderer.aoLightValueScratchXYZPPN = world.getBlock(x + 1, y, z - 1).getAmbientOcclusionLightValue();
        	renderer.aoBrightnessXYZPPN = block.getMixedBrightnessForBlock(world, x + 1, y, z - 1);
        }

        if (!flag4 && !flag3) {
        	renderer.aoLightValueScratchXYZNPP = renderer.aoLightValueScratchXYNP;
        	renderer.aoBrightnessXYZNPP = renderer.aoBrightnessXYNP;
        } else {
        	renderer.aoLightValueScratchXYZNPP = world.getBlock(x - 1, y, z + 1).getAmbientOcclusionLightValue();
        	renderer.aoBrightnessXYZNPP = block.getMixedBrightnessForBlock(world, x - 1, y, z + 1);
        }

        if (!flag4 && !flag2) {
        	renderer.aoLightValueScratchXYZPPP = renderer.aoLightValueScratchXYPP;
        	renderer.aoBrightnessXYZPPP = renderer.aoBrightnessXYPP;
        } else {
        	renderer.aoLightValueScratchXYZPPP = world.getBlock(x + 1, y, z + 1).getAmbientOcclusionLightValue();
        	renderer.aoBrightnessXYZPPP = block.getMixedBrightnessForBlock(world, x + 1, y, z + 1);
        }


        i1 = l;

        f7 = world.getBlock(x, y + 1, z).getAmbientOcclusionLightValue();
        f6 = (renderer.aoLightValueScratchXYZNPP + renderer.aoLightValueScratchXYNP + renderer.aoLightValueScratchYZPP + f7) / 4.0F;
        f3_2 = (renderer.aoLightValueScratchYZPP + f7 + renderer.aoLightValueScratchXYZPPP + renderer.aoLightValueScratchXYPP) / 4.0F;
        f4 = (f7 + renderer.aoLightValueScratchYZPN + renderer.aoLightValueScratchXYPP + renderer.aoLightValueScratchXYZPPN) / 4.0F;
        f5 = (renderer.aoLightValueScratchXYNP + renderer.aoLightValueScratchXYZNPN + f7 + renderer.aoLightValueScratchYZPN) / 4.0F;
        renderer.brightnessTopRight = renderer.getAoBrightness(renderer.aoBrightnessXYZNPP, renderer.aoBrightnessXYNP, renderer.aoBrightnessYZPP, i1);
        renderer.brightnessTopLeft = renderer.getAoBrightness(renderer.aoBrightnessYZPP, renderer.aoBrightnessXYZPPP, renderer.aoBrightnessXYPP, i1);
        renderer.brightnessBottomLeft = renderer.getAoBrightness(renderer.aoBrightnessYZPN, renderer.aoBrightnessXYPP, renderer.aoBrightnessXYZPPN, i1);
        renderer.brightnessBottomRight = renderer.getAoBrightness(renderer.aoBrightnessXYNP, renderer.aoBrightnessXYZNPN, renderer.aoBrightnessYZPN, i1);
        renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = f1;
        renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = f2;
        renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = f3;
        renderer.colorRedTopLeft *= f3_2;
        renderer.colorGreenTopLeft *= f3_2;
        renderer.colorBlueTopLeft *= f3_2;
        renderer.colorRedBottomLeft *= f4;
        renderer.colorGreenBottomLeft *= f4;
        renderer.colorBlueBottomLeft *= f4;
        renderer.colorRedBottomRight *= f5;
        renderer.colorGreenBottomRight *= f5;
        renderer.colorBlueBottomRight *= f5;
        renderer.colorRedTopRight *= f6;
        renderer.colorGreenTopRight *= f6;
        renderer.colorBlueTopRight *= f6;
        renderer.renderFaceYPos(block, (double)x, (double)y, (double)z, renderer.getBlockIcon(block, world, x, y, z, 1));
        flag = true;
        
        /*
         *   End AO code
         */
        
        //Top Side
        tess.setColorOpaque_F(renderer.colorRedBottomRight, renderer.colorGreenBottomRight, renderer.colorBlueBottomRight);
        tess.setBrightness(renderer.brightnessBottomRight);
        tess.addVertexWithUV(x,   y+nwQ, z,   u1, v1); //NW
        
        tess.setColorOpaque_F(renderer.colorRedTopRight, renderer.colorGreenTopRight, renderer.colorBlueTopRight);
        tess.setBrightness(renderer.brightnessTopRight);
        tess.addVertexWithUV(x,   y+swQ, z+1, u1, v0); //SW
        
        tess.setColorOpaque_F(renderer.colorRedTopLeft, renderer.colorGreenTopLeft, renderer.colorBlueTopLeft);
        tess.setBrightness(renderer.brightnessTopLeft);
        tess.addVertexWithUV(x+1, y+seQ, z+1, u0, v0); //SE
        
        tess.setColorOpaque_F(renderer.colorRedBottomLeft, renderer.colorGreenBottomLeft, renderer.colorBlueBottomLeft);
        tess.setBrightness(renderer.brightnessBottomLeft);
        tess.addVertexWithUV(x+1, y+neQ, z,   u0, v1); //NE
        
        //North Side
        tess.setColorOpaque(204, 204, 204);
        tess.addVertexWithUV(x+1, y+e, z, u1_2, v1_2);
        tess.addVertexWithUV(x+1, y,   z, u1_2, v0_2);
        tess.addVertexWithUV(x,   y,   z, u0_2, v0_2);
        tess.addVertexWithUV(x,   y+w, z, u0_2, v1_2);
        
        //East Side
        tess.setColorOpaque(153, 153, 155);
        tess.addVertexWithUV(x+1, y+s, z+1, u1_2, v1_2);
        tess.addVertexWithUV(x+1, y,   z+1, u1_2, v0_2);
        tess.addVertexWithUV(x+1, y,   z,   u0_2, v0_2);
        tess.addVertexWithUV(x+1, y+n, z,   u0_2, v1_2);
        
        //South Side
        tess.setColorOpaque(204, 204, 204);
        tess.addVertexWithUV(x,   y+w, z+1, u1_2, v1_2);
        tess.addVertexWithUV(x,   y,   z+1, u1_2, v0_2);
        tess.addVertexWithUV(x+1, y,   z+1, u0_2, v0_2);
        tess.addVertexWithUV(x+1, y+e, z+1, u0_2, v1_2);

        //West Side
        tess.setColorOpaque(153, 153, 155);
        tess.addVertexWithUV(x,   y+n, z,   u1_2, v1_2);
        tess.addVertexWithUV(x,   y,   z,   u1_2, v0_2);
        tess.addVertexWithUV(x,   y,   z+1, u0_2, v0_2);
        tess.addVertexWithUV(x,   y+s, z+1, u0_2, v1_2);

        //Bottom Side
        tess.setColorOpaque(127, 127, 127);
        tess.addVertexWithUV(x,   y,   z+1, u1_2, v1_2);
        tess.addVertexWithUV(x,   y,   z, u1_2, v0_2);
        tess.addVertexWithUV(x+1, y,   z, u0_2, v0_2);
        tess.addVertexWithUV(x+1, y,   z+1, u0_2, v1_2);
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return false;
	}

	@Override
	public int getRenderId() {
		return ClientProxy.roadBlockRenderID;
	}
	
	public double quadHeight(int meta) {
		double m = meta + 1.0;
		return ((1.0 / 16.0) * m);
	}
}