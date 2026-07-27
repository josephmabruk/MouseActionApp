import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * RESEARCH IMPLEMENTATION:
 * FRAUD AND SCAMS IN KENYA: AN ANALYSIS OF SYSTEMIC VULNERABILITIES,
 * REPOSITORIES AND THREAT MITIGATION LANDSCAPES
 * 
 * Author: Joseph Mabruk Taskuru Juma
 * Reg No: DCS/N/2023/05/25
 * Institution: Kabarak University - School of Science, Engineering and Technology
 */

public class FraudMitigationSystem {

    // Model representing a threat indicator log
    static class ThreatRecord {
        private String indicator;
        private String threatCategory;
        private String reportedBy;
        private String riskLevel;
        private String timestamp;

        public ThreatRecord(String indicator, String threatCategory, String reportedBy, String riskLevel) {
            this.indicator = indicator;
            this.threatCategory = threatCategory;
            this.reportedBy = reportedBy;
            this.riskLevel = riskLevel;
            this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }

        @Override
        public String toString() {
            return "[" + timestamp + "] Indicator: " + indicator + 
                   " | Category: " + threatCategory + 
                   " | Risk: " + riskLevel + 
                   " | Source: " + reportedBy;
        }
    }

    // Service implementing the 3-stage Workflow
    static class ThreatRegistryService {
        private Map<String, ThreatRecord> registry = new HashMap<>();
        private List<String> institutionalPartners = new ArrayList<>();

        public ThreatRegistryService() {
            institutionalPartners.add("Directorate of Criminal Investigations (DCI)");
            institutionalPartners.add("Communications Authority of Kenya (CA / KE-CIRT)");
            institutionalPartners.add("Central Bank of Kenya (CBK)");
            institutionalPartners.add("Mobile Network Operators (MNOs / Safaricom / Airtel)");

            // Pre-seed known threats
            registry.put("0700000000", new ThreatRecord("0700000000", "SMS Phishing (Smishing)", "KE-CIRT", "CRITICAL"));
            registry.put("0711111111", new ThreatRecord("0711111111", "Voice Call Impersonation (Vishing)", "DCI Portal", "HIGH"));
        }

        public void verifyIndicator(String indicator) {
            System.out.println("\n[STAGE 1: INGESTION] Querying Centralized Real-Time Registry (< 5 seconds)...");
            System.out.println("[STAGE 2: VERIFICATION] Cross-referencing threat indicators...");

            if (registry.containsKey(indicator)) {
                ThreatRecord record = registry.get(indicator);
                System.out.println("\n------------------------------------------------");
                System.out.println("⚠️ ALERT: HIGH-RISK THREAT IDENTIFIED!");
                System.out.println(record);
                System.out.println("RECOMMENDATION: Do not transfer funds or share PIN/OTP.");
                System.out.println("------------------------------------------------");
            } else {
                System.out.println("\n------------------------------------------------");
                System.out.println("✅ STATUS: CLEAN / UNFLAGGED");
                System.out.println("No matching threat reports found for: " + indicator);
                System.out.println("------------------------------------------------");
            }
        }

        public void reportScam(String indicator, String categoryChoice) {
            String category;
            String risk;

            switch (categoryChoice) {
                case "1":
                    category = "SMS Phishing (Smishing)";
                    risk = "HIGH";
                    break;
                case "2":
                    category = "Voice Call Impersonation (Vishing)";
                    risk = "CRITICAL";
                    break;
                case "3":
                    category = "Social Engineering / Impersonation";
                    risk = "MEDIUM";
                    break;
                default:
                    category = "Unclassified Digital Fraud";
                    risk = "MEDIUM";
                    break;
            }

            ThreatRecord newReport = new ThreatRecord(indicator, category, "Public User", risk);
            registry.put(indicator, newReport);

            System.out.println("\n[STAGE 3: DISTRIBUTION] Threat logged into central registry in < 5 seconds.");
            System.out.println("Syncing security alerts with institutional partners (< 1 minute):");
            for (String partner : institutionalPartners) {
                System.out.println("  ➜ Broadcast sent to: " + partner);
            }
            System.out.println("\nSuccess: System updated to protect other users in real time.");
        }

        public void listInstitutions() {
            System.out.println("\n--- CONNECTED ANTI-FRAUD REPOSITORIES ---");
            for (String partner : institutionalPartners) {
                System.out.println("• " + partner);
            }
        }
    }

    // Main Driver Method
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ThreatRegistryService registryService = new ThreatRegistryService();
        boolean active = true;

        System.out.println("================================================================");
        System.out.println("   KABARAK UNIVERSITY - RESEARCH PROJECT CODE IMPLEMENTATION   ");
        System.out.println("   Title: Fraud and Scams in Kenya (Threat Mitigation Engine)   ");
        System.out.println("================================================================");

        while (active) {
            System.out.println("\nMAIN MENU:");
            System.out.println("1. Public Verification (Check Phone Number/Domain)");
            System.out.println("2. Report a Fraud/Scam Incident");
            System.out.println("3. View Integrated Institutional Repositories");
            System.out.println("4. Exit");
            System.out.print("Select an option (1-4): ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.print("\nEnter phone number or link to verify (e.g., 0700000000): ");
                    String checkInput = scanner.nextLine().trim();
                    registryService.verifyIndicator(checkInput);
                    break;

                case "2":
                    System.out.print("\nEnter suspect phone number or link: ");
                    String scamInput = scanner.nextLine().trim();
                    System.out.println("Select Scam Category:");
                    System.out.println("  1. SMS Phishing (Smishing)");
                    System.out.println("  2. Voice Call Impersonation (Vishing)");
                    System.out.println("  3. Social Engineering");
                    System.out.print("Choice (1-3): ");
                    String catChoice = scanner.nextLine().trim();
                    
                    registryService.reportScam(scamInput, catChoice);
                    break;

                case "3":
                    registryService.listInstitutions();
                    break;

                case "4":
                    active = false;
                    System.out.println("\nExiting system. Security session terminated.");
                    break;

                default:
                    System.out.println("\nInvalid selection. Please choose an option from 1 to 4.");
            }
        }

        scanner.close();
    }
}