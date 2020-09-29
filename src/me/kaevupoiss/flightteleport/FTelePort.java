package me.kaevupoiss.flightteleport;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class FTelePort implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            //Vector velocity = player.getVelocity();
            Location loc = player.getLocation();

            float x = Float.parseFloat(strings[0]);
            float y = Float.parseFloat(strings[1]);
            float z = Float.parseFloat(strings[2]);

            Vector travelVector = new Vector(x - loc.getX(), y - loc.getY(), z - loc.getZ());
            double movementLength = travelVector.length();
            Vector particleIncrement = travelVector.multiply(1 / (3 * movementLength));

            float particlex;
            float particley;
            float particlez;

            task = Bukkit.getScheduler().scheduleSyncRepeatingTask(FlightTeleport.getPlugin(FlightTeleport.class), new Runnable() {
                public void run() {
                    for (int i = 0; i < (movementLength * 3); i++) {
                        //TODO: generate more particles than 100 particles.
                        particlex = (float) (loc.getX() + i * particleIncrement.getX());
                        particley = (float) (loc.getY() + i * particleIncrement.getY());
                        particlez = (float) (loc.getZ() + i * particleIncrement.getZ());
                        player.getWorld().spawnParticle(Particle.FLAME, particlex, particley, particlez, 1, 0.0, 0.0, 0.0, 0);
                    }
                }
            });
            Location targetLocation = new Location(player.getWorld(), x, y, z, player.getLocation().getYaw(), player.getLocation().getPitch());

            player.teleport(targetLocation);
            return true;
        }
        return false;
    }
}
