package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.Entity;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author Emil
 */
public class Player extends Entity {

    @Override
    protected void onLifeZero() {
        updateScoreOnClient("http://localhost:8080/reset");
        System.out.println("Score reset.");
    }

    public Player(int life) {
        super(life);
    }

    public void updateScoreOnClient(String url) {
        try {
            URL updateUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) updateUrl.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Score updated successfully.");
            } else {
                System.out.println("Failed to update score. Response code: " + responseCode);
            }

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
