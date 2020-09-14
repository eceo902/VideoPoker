import java.util.Arrays;

public class VideoPoker
{
    private Card[] deck, hand;
    private double cash, bet, multiplier;

    public VideoPoker(double cash, double bet)
    {
        this.cash = cash;
        this.bet = bet;

        deck = new Card[52];

        int i = 0;
        for (int rank = 2; rank < 15; rank++)
            for (int suit = 0; suit < 4; suit++)
            {
                deck[i++] = new Card(rank, suit);
            }

        hand = new Card[5];
    }



    public void deal()
    {
        shuffle();

        for (int i = 0; i < hand.length; i++)
        {
            hand[i] = deck[i];
        }
    }

    private void shuffle()
    {
        for (int i = 0; i < deck.length; i++)
        {
            Card temp = deck[i];
            int randy = (int) (Math.random() * deck.length);
            deck[i] = deck[randy];
            deck[randy] = temp;
        }
    }

    /**
     * For each false element in keep, replace
     *  the Card in hand at the corresponding
     *  index with a Card from deck.
     *
     * Precondition: keep.length == hand.length
     *
     * @param keep
     */
    public void draw(boolean[] keep)
    {
        for (int i = 0; i < keep.length; i++)
        {
            if(keep[i])
                hand[i] = deck[i + hand.length];
        }
    }

    public double getCash()
    {
        return cash;
    }

    public Card[] getHand()
    {
        return hand;
    }

    public boolean hasPair()
    {
        int[] counts = counts();
    }

    private int[] counts()
    {
        int[] counts = new int[hand.length];

        for (int i = 0; i < hand.length; i++)
        {
            counts[i] = count(hand[i]);
        }

        return counts;
    }

    public void payout()
    {
        cash += bet * multiplier;
    }

    private int count(Card card)
    {
        int count = 0;

        for (Card kard : hand)
        {
            if(kard.getRank() == card.getRank())
                count++;
        }

        return count;
    }

    private int count(int[] counts, int x)
    {
        int count = 0;

        for(int ecks : counts)

    }

    public boolean hasThreeOfAKind()
    {
        return count(counts(), 3) == 3;
    }

    public boolean hasStraight()
    {
        int[] ranks = new int[hand.length];

        for (int i = 0; i < ranks.length; i++)
        {
            ranks[i] = hand[i].getRank();
        }

        Arrays.sort(ranks);

        if(Arrays.equals(ranks, new int[]{2, 3, 4, 5, 14}));

        for (int i = 0; i < ranks.length - 1; i++)
        {
            if(ranks[i] + 1 != ranks[i + 1])
                return false;
        }

        return true;
    }

    public boolean hasTwoPair()
    {
        return count(counts(), 2) == 4;
    }

    public boolean hasFlush()
    {
        int suit = hand[0].getSuit();

        for (int i = 1; i < hand.length; i++)
        {
            if(suit != hand[i].getSuit())
                return false;
        }

        return true;
    }

    public boolean hasFullHouse()
    {
        return hasPair() && hasThreeOfAKind();
    }

    public boolean hasFourOfAKind()
    {
        return count(counts(), 4) == 4;
    }

    public boolean hasStraightFlush()
    {
        return hasStraight() && hasFlush();
    }

    public String getHandStrength()
    {
        if(hasStraightFlush())
        {
            multiplier = 50;
            return "Straight Flush";
        }

        if(hasFourOfAKind())
        {
            multiplier = 25;
            return "Four of a Kind";
        }

        if(hasFullHouse())
        {
            multiplier = 9;
            return "Full House";
        }

        if(hasFlush())
        {
            multiplier = 6;
            return "Flush";
        }

        if(hasStraight())
        {
            multiplier = 4;
            return "Straight";
        }

        if(hasThreeOfAKind())
        {
            multiplier = 3;
            return "Three of a Kind";
        }

        if(hasTwoPair())
        {
            multiplier = 2;
            return "Two Pair";
        }

        if(hasPair())
        {
            multiplier = 1;
            return "Pair";
        }
    }
}