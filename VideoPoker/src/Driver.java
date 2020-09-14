import java.util.Arrays;
import java.util.Scanner;

public class Driver
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to VideoPoker!");
        System.out.print("How much cash are you playing with? ");
        double cash = input.nextDouble();
        System.out.print("How much would you like to bet on each hand? ");
        double bet = input.nextDouble();

        VideoPoker game = new VideoPoker(cash, bet);

        while(game.getCash() >= bet)
        {
            game.deal();
            Card[] hand = game.getHand();
            System.out.println(Arrays.toString(hand));

            boolean[] keep = new boolean[hand.length];

            for (int i = 0; i < hand.length; i++)
            {
                System.out.println("Do you want to keep " + hand[i] + "? ");
                if (input.nextInt() == 1)
                    keep[i] = true;
            }

            game.draw(keep);
            System.out.println(Arrays.toString(hand) + " - " + game.getHandStrength());
            game.payout();
            System.out.println("Cash: $" + game.getCash() + "\n");
        }
    }
}
