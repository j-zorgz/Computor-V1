import java.util.ArrayList;
import java.util.Collections;

public class Polynome {

	private ArrayList<Monome> pol;

	Polynome() {
		pol = new ArrayList<Monome>();
	}

	Polynome (String arg) throws Exception {
		this();
		
		int 	signe;
		double	coefficient;
		double	puissance;
		int		i;
		int		len;

		i = 0;
			while (i < arg.length()) {
//				System.out.println("[" + arg.substring(i) + "]");
				while (arg.charAt(i) == ' ')
					i++;
				if (arg.charAt(i) == '-')
				{
					signe = -1;
					i++;
				}
				else
				{
					signe = 1;
					if (arg.charAt(i) == '+')
						i++;
				}
				len = 0;
				while (arg.charAt(i) == ' ')
					i++;
				while (Character.isDigit(arg.charAt(i + len)) || arg.charAt(i + len) == '.') {
					len++;
				}
				coefficient = Double.parseDouble(arg.substring(i, i + len)) * signe;
				i += len;
				if (arg.charAt(i +  1) == '*') {
					i += 4;
					if (i > arg.length()) {
						throw new ParseException("Unexpected end");
					}
					if (arg.charAt(i) != '^') {
						puissance = 1;
					}
					else {
						i++;
						len = 0;
						if (arg.charAt(i) == '-')
						{
							signe = -1;
							i++;
						}
						else
							signe = 1;
						len = 0;
						while (Character.isDigit(arg.charAt(i + len)) || arg.charAt(i + len) == '.') {
							len++;
						}
						puissance = Double.parseDouble(arg.substring(i, i + len)) * signe;
						i += len;
					}
					if (puissance % 1 != 0 || puissance < 0) {
						throw new ParseException("It seems you gave a not natural power value (" + puissance + ")");
					}
				}
				else {
					puissance = 0;
				}
				add_monome(new Monome((int)puissance, coefficient));
				while (arg.charAt(i) == ' ')
					i++;
				if (arg.charAt(i) == '=')
					break;
			}
			i++;
			while (i < arg.length()) {
//				System.out.println("[" + arg.substring(i) + "]");
				while (arg.charAt(i) == ' ')
					i++;
				if (arg.charAt(i) == '-')
				{
					signe = -1;
					i++;
				}
				else
				{
					signe = 1;
					if (arg.charAt(i) == '+')
						i++;
				}
				len = 0;
				while (arg.charAt(i) == ' ')
					i++;
				while (Character.isDigit(arg.charAt(i + len)) || arg.charAt(i + len) == '.') {
					len++;
				}
				coefficient = Double.parseDouble(arg.substring(i, i + len)) * signe;
//				System.out.println("coef : " + coefficient);
				i += len;
				if (arg.charAt(i +  1) == '*') {
					i += 4;
					if (i > arg.length()) {
						throw new ParseException("Unexpected end");
					}
					if (arg.charAt(i) != '^') {
						puissance = 1;
					}
					else {
						i++;
						len = 0;
						if (arg.charAt(i) == '-')
						{
							signe = -1;
							i++;
						}
						else
							signe = 1;
						len = 0;
						while (i + len < arg.length() && (Character.isDigit(arg.charAt(i + len)) || arg.charAt(i + len) == '.')) {
							len++;
						}
						puissance = Double.parseDouble(arg.substring(i, i + len)) * signe;
						i += len;
					}
					if (puissance % 1 != 0 || puissance < 0) {
						throw new ParseException("It seems you gave a not natural power value (" + puissance + ")");
					}
				}
				else {
					puissance = 0;
				}
				add_monome(new Monome((int)puissance, coefficient * -1));
				while (i + len < arg.length() && arg.charAt(i) == ' ')
					i++;
			}
		}
	
	public void sort() {
		Collections.sort(pol);
	}

	public void add_monome(Monome to_add) {
		Monome	i;
		int		n;
		
		n = 0;
		while (n < pol.size()) {
			i = pol.get(n);
			if (i.getPuissance() == to_add.getPuissance()) {
				i.add(to_add);
				return ;
			}
			n++;
		}
		pol.add(to_add);
	}
	
	public String toString()
	{
		String ret = new String();
		for (Monome i : pol) {
			if (i.getCoef() >= 0)
				ret += "+ ";
			ret += i + " ";
		}
		return ret;
	}
	
	public Monome get_monome_deg(int pdegre) {
		for (Monome i : pol) {
			if (i.getPuissance() == pdegre)
				return (i);
		}
		return null;
	}
	
	public int getDegre() {
		return (pol.get(0).getPuissance());
	}
}
