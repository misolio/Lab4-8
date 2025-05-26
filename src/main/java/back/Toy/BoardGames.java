package back.Toy;

public class BoardGames extends Toy {
    private int minNumberOfPlayers;
    private int maxNumberOfPlayers;
    private int playingTime;

    public BoardGames(String name, String type, int price, int size, String material, int ageRestrictions,
                       int minNumberOfPlayers, int maxNumberOfPlayers, int playingTime) {
        super(name, type, price, size, material, ageRestrictions);
        this.minNumberOfPlayers = minNumberOfPlayers;
        this.maxNumberOfPlayers = maxNumberOfPlayers;
        this.playingTime = playingTime;
    }

    public int getMinNumberOfPlayers() {
        return minNumberOfPlayers;
    }

    public int getMaxNumberOfPlayers() {
        return maxNumberOfPlayers;
    }

    public int getPlayingTime() {
        return playingTime;
    }

    @Override
    public int calculateSize() {
        return minNumberOfPlayers + maxNumberOfPlayers;
    }

    @Override
    public String toString() {
        return super.toString() + '\'' +
                ", \nкількість гравців: " + minNumberOfPlayers +
                " - " + maxNumberOfPlayers +
                ", \nчас гри:" + playingTime +
                ", \nрозмір:" + calculateSize();
    }
}
