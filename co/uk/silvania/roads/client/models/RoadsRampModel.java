package co.uk.silvania.roads.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class RoadsRampModel extends ModelBase
{
  //fields
    ModelRenderer Back;
    ModelRenderer Ramp4;
    ModelRenderer Ramp2;
    ModelRenderer Ramp3;
    ModelRenderer Ramp1;
    ModelRenderer Ramp4b;
    ModelRenderer Ramp1b;
    ModelRenderer Ramp1c;
    ModelRenderer Ramp1d;
    ModelRenderer Shape1;
    ModelRenderer Ramp3b;
    ModelRenderer Ramp2b;
  
  public RoadsRampModel()
  {
    textureWidth = 128;
    textureHeight = 128;
    
      Back = new ModelRenderer(this, 0, 70);
      Back.addBox(0F, 0F, 0F, 16, 15, 1);
      Back.setRotationPoint(-8F, 9F, 55F);
      Back.setTextureSize(128, 128);
      Back.mirror = true;
      setRotation(Back, 0F, 0F, 0F);
      Ramp4 = new ModelRenderer(this, 0, 0);
      Ramp4.addBox(-8F, 0F, -0.5F, 16, 16, 1);
      Ramp4.setRotationPoint(0F, 8.5F, 56F);
      Ramp4.setTextureSize(128, 128);
      Ramp4.mirror = true;
      setRotation(Ramp4, -1.332559F, 0F, 0F);
      Ramp2 = new ModelRenderer(this, 0, 34);
      Ramp2.addBox(-8F, 32F, -0.5F, 16, 16, 1);
      Ramp2.setRotationPoint(0F, 8.5F, 56F);
      Ramp2.setTextureSize(128, 128);
      Ramp2.mirror = true;
      setRotation(Ramp2, -1.332559F, 0F, 0F);
      Ramp3 = new ModelRenderer(this, 0, 17);
      Ramp3.addBox(-8F, 16F, -0.5F, 16, 16, 1);
      Ramp3.setRotationPoint(0F, 8.5F, 56F);
      Ramp3.setTextureSize(128, 128);
      Ramp3.mirror = true;
      setRotation(Ramp3, -1.332559F, 0F, 0F);
      Ramp1 = new ModelRenderer(this, 0, 51);
      Ramp1.addBox(-8F, 48F, 0.5F, 16, 18, 1);
      Ramp1.setRotationPoint(0F, 7.5F, 56F);
      Ramp1.setTextureSize(128, 128);
      Ramp1.mirror = true;
      setRotation(Ramp1, -1.332559F, 0F, 0F);
      Ramp4b = new ModelRenderer(this, 34, 51);
      Ramp4b.addBox(-8F, 1F, 0.5F, 15, 15, 3);
      Ramp4b.setRotationPoint(0.5F, 8.5F, 56F);
      Ramp4b.setTextureSize(128, 128);
      Ramp4b.mirror = true;
      setRotation(Ramp4b, -1.332559F, 0F, 0F);
      Ramp1b = new ModelRenderer(this, 34, 0);
      Ramp1b.addBox(-8F, 48F, 0.5F, 15, 14, 1);
      Ramp1b.setRotationPoint(0.5F, 8.5F, 56F);
      Ramp1b.setTextureSize(128, 128);
      Ramp1b.mirror = true;
      setRotation(Ramp1b, -1.332559F, 0F, 0F);
      Ramp1c = new ModelRenderer(this, 34, 15);
      Ramp1c.addBox(-8F, 48F, 1.5F, 15, 10, 1);
      Ramp1c.setRotationPoint(0.5F, 8.5F, 56F);
      Ramp1c.setTextureSize(128, 128);
      Ramp1c.mirror = true;
      setRotation(Ramp1c, -1.332559F, 0F, 0F);
      Ramp1d = new ModelRenderer(this, 34, 26);
      Ramp1d.addBox(-8F, 48F, 2.5F, 15, 6, 1);
      Ramp1d.setRotationPoint(0.5F, 8.5F, 56F);
      Ramp1d.setTextureSize(128, 128);
      Ramp1d.mirror = true;
      setRotation(Ramp1d, -1.332559F, 0F, 0F);
      Shape1 = new ModelRenderer(this, 34, 33);
      Shape1.addBox(0.5F, 0F, 0F, 15, 14, 4);
      Shape1.setRotationPoint(-8F, 10F, 51F);
      Shape1.setTextureSize(128, 128);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0F, 0F);
      Ramp3b = new ModelRenderer(this, 34, 51);
      Ramp3b.addBox(-8F, 16F, 0.5F, 15, 16, 3);
      Ramp3b.setRotationPoint(0.5F, 8.5F, 56F);
      Ramp3b.setTextureSize(128, 128);
      Ramp3b.mirror = true;
      setRotation(Ramp3b, -1.332559F, 0F, 0F);
      Ramp2b = new ModelRenderer(this, 34, 51);
      Ramp2b.addBox(-8F, 32F, 0.5F, 15, 16, 3);
      Ramp2b.setRotationPoint(0.5F, 8.5F, 56F);
      Ramp2b.setTextureSize(128, 128);
      Ramp2b.mirror = true;
      setRotation(Ramp2b, -1.332559F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Back.render(f5);
    Ramp4.render(f5);
    Ramp2.render(f5);
    Ramp3.render(f5);
    Ramp1.render(f5);
    Ramp4b.render(f5);
    Ramp1b.render(f5);
    Ramp1c.render(f5);
    Ramp1d.render(f5);
    Shape1.render(f5);
    Ramp3b.render(f5);
    Ramp2b.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }

}
