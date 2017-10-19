package CactusClusteringVisualization;

import javax.swing.JTextArea;


public class ShowFormat {

	public ShowFormat() {
		
	}
	
public void show_DM_Format(JTextArea Ta){
		
		Ta.setText("The recommended formats of Distance Matrix:\n");
		Ta.append("\n");
		Ta.append("\n");
		Ta.append("-------------------------   Format 1   -------------------------\n");
		Ta.append("Name\n");
		Ta.append("Sample 1       Sample 2     Sample 3     Sample 4         ......\n");
        Ta.append("Sample 1       0               d(1,2)       d(1,3)       d(1,4)       ......\n");
        Ta.append("Sample 2       d(2,1)        0              d(2,3)       d(2,4)       ......\n");
        Ta.append("Sample 3       d(3,1)        d(3,2)       0              d(3,4)       ......\n");
        Ta.append("Sample 4       d(4,1)        d(4,2)       d(4,3)       0              ......\n");
        Ta.append("      .                  .                .               .               .            ......\n");
        Ta.append("      .                  .                .               .               .            ......\n");
        Ta.append("      .                  .                .               .               .            ......\n");
        Ta.append("      .                  .                .               .               .            ......\n");
        Ta.append("      .                  .                .               .               .            ......\n");
        Ta.append("      .                  .                .               .               .            ......\n");
        Ta.append("\n");
		Ta.append("\n");
		Ta.append("-------------------------   Format 2   -------------------------\n");
		Ta.append("Sample 1       Sample 2     Sample 3     Sample 4         ......\n");
        Ta.append("Sample 1       0               d(1,2)       d(1,3)       d(1,4)       ......\n");
        Ta.append("Sample 2       d(2,1)        0              d(2,3)       d(2,4)       ......\n");
        Ta.append("Sample 3       d(3,1)        d(3,2)       0              d(3,4)       ......\n");
        Ta.append("Sample 4       d(4,1)        d(4,2)       d(4,3)       0              ......\n");
        Ta.append("      .                  .                .               .               .            ......\n");
        Ta.append("      .                  .                .               .               .            ......\n");
        Ta.append("      .                  .                .               .               .            ......\n");
        Ta.append("      .                  .                .               .               .            ......\n");
        Ta.append("      .                  .                .               .               .            ......\n");
        Ta.append("      .                  .                .               .               .            ......\n");
        Ta.append("\n");
		Ta.append("\n");
		Ta.append("-------------------------   Format 3   -------------------------\n");
		Ta.append("0               d(1,2)       d(1,3)       d(1,4)       ......\n");
        Ta.append("d(2,1)        0              d(2,3)       d(2,4)       ......\n");
        Ta.append("d(3,1)        d(3,2)       0              d(3,4)       ......\n");
        Ta.append("d(4,1)        d(4,2)       d(4,3)       0              ......\n");
        Ta.append("    .                .               .               .           ......\n");
        Ta.append("    .                .               .               .           ......\n");
        Ta.append("    .                .               .               .           ......\n");
        Ta.append("    .                .               .               .           ......\n");
        Ta.append("    .                .               .               .           ......\n");
        Ta.append("    .                .               .               .           ......\n");
     
	}

	public void show_A_Format(JTextArea Ta){
		
		Ta.setText("The recommended format of attributes:\n");
		Ta.append("\n");
        Ta.append("      Attribute 1                   Attribute 2                   Attribute 3             ......\n");
        Ta.append("Label for Sample 1       Label for Sample 1       Label for Sample 1       ......\n");
        Ta.append("Label for Sample 2       Label for Sample 2       Label for Sample 2       ......\n");
        Ta.append("Label for Sample 3       Label for Sample 3       Label for Sample 3       ......\n");
        Ta.append("Label for Sample 4       Label for Sample 4       Label for Sample 4       ......\n");
        Ta.append("           .                                   .                                   .                        ......\n");
        Ta.append("           .                                   .                                   .                        ......\n");
        Ta.append("           .                                   .                                   .                        ......\n");
        Ta.append("           .                                   .                                   .                        ......\n");
        Ta.append("           .                                   .                                   .                        ......\n");
        Ta.append("           .                                   .                                   .                        ......\n");
        
	}

}
