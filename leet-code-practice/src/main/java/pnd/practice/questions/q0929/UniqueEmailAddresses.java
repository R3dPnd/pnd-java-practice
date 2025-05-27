/*
 * 929. Unique Email Addresses
 * 
 * Every valid email consists of a local name and a domain name, separated by the '@' sign. 
 * Besides lowercase letters, the email may contain one or more '.' or '+'.
 * 
 * For example, in "alice@leetcode.com", "alice" is the local name, and "leetcode.com" is the domain name.
 * 
 * If you add periods '.' between some characters in the local name part of an email address, mail sent there will be forwarded to the same address without dots in the local name. 
 * For example, "alice.z@leetcode.com" and "alicez@leetcode.com" forward to the same email address.
 * 
 * If you add a plus '+' in the local name, everything after the first plus sign will be ignored. 
 * This allows certain emails to be filtered, for example m.y+name@email.com will be forwarded to my@email.com.
 * 
 */

package pnd.practice.questions.q0929;

import java.util.Set;
import java.util.HashSet;

public class UniqueEmailAddresses {
    private Set<String> emailSet = new HashSet<>();

    public int numUniqueEmails(String[] emails) {
        for (String email : emails) {
            String[] parts = email.split("@");
            String localName = parts[0];
            String domainName = parts[1];

            localName = localName.replace(".", "");
            if (localName.contains("+")) {
                localName = localName.substring(0, localName.indexOf("+"));
            }
            String cleanedEmail = localName + "@" + domainName;
            emailSet.add(cleanedEmail);
        }
        return emailSet.size();
    }
}
