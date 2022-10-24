package xyz.someboringnerd.someboringclient.Modules.MOVEMENT;

import net.minecraft.client.Minecraft;
import net.minecraft.init.MobEffects;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import xyz.someboringnerd.someboringclient.Modules.CATEGORY;
import xyz.someboringnerd.someboringclient.Modules.Module;

public class Strafe extends Module
{
    public Strafe(String name, CATEGORY cat)
    {
        super(name, cat);

        Create();
    }

    @SubscribeEvent
    public void PlayerMovementEvent(TickEvent.PlayerTickEvent event)
    {
        Minecraft mc = Minecraft.getMinecraft();



        if (mc.player.isInLava() || mc.player.isInWater() || mc.player.isOnLadder()){
            return;
        }

        double speedY = 1;

        if(mc.player.onGround)
        {
            if (mc.player.isPotionActive(MobEffects.JUMP_BOOST)) {
                speedY += (mc.player.getActivePotionEffect(MobEffects.JUMP_BOOST).getAmplifier() + 1) * 0.1f;
                /*
                event.player.s(mc.player.motionY = speedY);
                */
            }
        }
    }
}
