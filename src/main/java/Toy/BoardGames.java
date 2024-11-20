package Toy;

public class BoardGames extends Toy {
    private String typeOfGame;
    private int minNumberOfPlayers;
    private int maxNumberOfPlayers;
    private int playingTime;

    public BoardGames(String name, String type, int price, int size, String material, int ageRestrictions, String gameType,
                      String typeOfGame, int minNumberOfPlayers, int maxNumberOfPlayers, int playingTime) {
        super(name, type, price, size, material, ageRestrictions, gameType);
        this.typeOfGame = typeOfGame;
        this.minNumberOfPlayers = minNumberOfPlayers;
        this.maxNumberOfPlayers = maxNumberOfPlayers;
        this.playingTime = playingTime;
    }

    public String getTypeOfGame() {
        return typeOfGame;
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
        return super.toString() +
                "вид='" + typeOfGame + '\'' +
                ", мінімальна кількість гравців=" + minNumberOfPlayers +
                ", максимальна кількість гравців=" + maxNumberOfPlayers +
                ", час гри=" + playingTime +
                ", розмір=" + calculateSize() +
                '}';
    }
}
