package defensa;
import partPlanetas.planeta;
public class MissileLauncher extends Defense{
	// contructor
		public MissileLauncher(int Armor, int baseDamage) {
			super(Armor, baseDamage);
			//falta el nivel
			Armor = ARMOR_MISSILELAUNCHER +(1*PLUS_ARMOR_MISSILELAUNCHER_BY_TECHNOLOGY)*1000/100;
			baseDamage = BASE_DAMAGE_MISSILELAUNCHER +(1*PLUS_ATTACK_MISSILELAUNCHER_BY_TECHNOLOGY)*1000/100;
		}
		public MissileLauncher() {
			super(ARMOR_MISSILELAUNCHER,BASE_DAMAGE_MISSILELAUNCHER);
		}

		// implementacion de las variables y militaryUnit

		public int attack() {
			return BASE_DAMAGE_MISSILELAUNCHER;
		}

		 
		public void tekeDamage(int receivedDamage) {
			setArmor(getActualArmor() - receivedDamage);
		}

		 
		public int getActualArmor() {
			return getArmor();
		}

		 
		public int getMetalCost() {
			return METAL_COST_MISSILELAUNCHER;
		}

		 
		public int getDeuteriumCost() {
			return DEUTERIUM_COST_MISSILELAUNCHER;
		}

		 
		public int getChanceGeneratinWaste() {
			return CHANCE_GENERATNG_WASTE_MISSILELAUNCHER;
		}

		 
		public int getChanceAttackAgain() {
			return CHANCE_ATTACK_AGAIN_MISSILELAUNCHER;
		}

		 
		public void resetArmor() {
			setArmor(ARMOR_MISSILELAUNCHER);
		}

}
