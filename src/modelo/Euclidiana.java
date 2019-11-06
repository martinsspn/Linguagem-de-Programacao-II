package modelo;

import java.util.ArrayList;
import java.util.List;

import controle.Imagem;
import controle.Knn;

public class Euclidiana extends Knn {
	public String KnnFunction(int g, ArrayList<Imagem> listaImagens, List<Float> listaAtributos) {
		float [] menores = new float[g];
		Imagem [] menord = new Imagem[g];
		float soma = 0;
		float aux = 0;
		float aux3 = 0;
		float dif = 0;
		int aux2 = 0;
		int aux4 = 0;
		int contperson = 0;
		int contnotperson = 0;
		String person = listaImagens.get(0).getClasse();
		for(int i=0; i<100; i++) {
			Imagem a = listaImagens.get(i);
			ArrayList<Float> b = a.getLista();
			dif = 0;
			for(int j = 0; j < 999; j++) {
				dif = listaAtributos.get(j) - b.get(j);
				dif = (float) Math.pow(dif, 2);
				soma = soma + dif;
			}
			soma = (float) Math.sqrt(soma);
			
			if(i < g) {
				menores[i] = soma;
				menord[i] = a;
			}
			else {
				for(int k=0; k<g; k++) {
					if(k == 0) {
						aux = menores[k];
						aux2 = 0;
						}
					else {
						if(menores[k]>aux) {
							aux = menores[k];
							aux2 = k;
						}
					}
				}
				if(soma < aux) {
					menores[aux2] = soma;
					menord[aux2] = a;
				}
			}
			soma = 0;
		}
		for (int i=0; i<g; i++) {
			if(menord[i].getClasse().equals(person)){
				contperson = contperson + 1;
			}
			else{
				contnotperson = contnotperson + 1;
			}
			if(i == 0) {
				aux3 = menores[i];
			}
			else {
				if(menores[i] < aux3) {
					aux3 = menores[i];
					aux4 = i;
				}
			}
		}
		if(contperson == contnotperson) {
			if(menord[aux4].getClasse().equals(person)) {
				return "há pessoas";
			}
			else{
				return "não há pessoas";
			}
			
		}
		
		if (contperson > contnotperson) {
			return "há pessoas";
		}
		else{
			return "não há pessoas";
		}
	}
}