package xyz.someboringnerd.someboringclient.Modules.COMBAT;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import xyz.someboringnerd.someboringclient.Modules.CATEGORY;
import xyz.someboringnerd.someboringclient.Modules.Module;
import xyz.someboringnerd.someboringclient.Util.ChatUtil;

import java.util.Locale;

public class KillAura extends Module
{
    private boolean isAttacking = false;

    public KillAura(String name, CATEGORY cat)
    {
        super(name, cat);

        settings.put("Range", 4);
        settings.put("SmartDelay", true);

        Create();
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event)
    {
        if(!isEnabled() && !isAttacking) return;

        Minecraft mc = Minecraft.getMinecraft();

        if (mc.player == null || !mc.player.isEntityAlive()) return;

        for (Entity entity : mc.world.loadedEntityList)
        {
            if(entity != null) {
                if (entity instanceof EntityLivingBase && !basicChecksEntity(entity))
                {
                    if (mc.player.getDistanceSq(entity) <= Float.parseFloat(settings.get("Range").toString())) {
                        if (settings.get("SmartDelay").toString().toLowerCase(Locale.ROOT).equals("true") && mc.player.getCooldownPeriod() == 0) {
                            attack(entity);
                            return;
                        } else if (settings.get("SmartDelay").toString().toLowerCase(Locale.ROOT).equals("false")) {
                            attack(entity);
                            return;
                        }
                    }
                }
            }
        }
    }

    private void attack(Entity e) {
        if (Minecraft.getMinecraft().player.getCooledAttackStrength(0.0f) >= 1.0f) {
            isAttacking = true;
            Minecraft.getMinecraft().playerController.attackEntity(Minecraft.getMinecraft().player, e);
            Minecraft.getMinecraft().player.swingArm(EnumHand.MAIN_HAND);
            isAttacking = false;
        }
    }

    public static boolean basicChecksEntity(Entity pl) {
        return pl.getName().equals(Minecraft.getMinecraft().player.getName()) || pl.isDead;
    }

}
