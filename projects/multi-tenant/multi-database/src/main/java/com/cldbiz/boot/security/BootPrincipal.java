package com.cldbiz.boot.security;

import java.io.Serializable;
import java.security.Principal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;




public class BootPrincipal implements Principal, Serializable {

	private static final long serialVersionUID = -247558099298617110L;

	// TODO: Privileges will be more abstract
	public static enum Privilege {
		VIEW(1), ADD(2), EDIT(3), DELETE(4);
		
		private int id;
		
		Privilege(int id) {
			this.id = id;
		}
		
		public String toString() {
			return super.toString().toLowerCase();
		}
	}
	
	// <refKey, privileges[]>
	private Map<String, Set<Privilege>> privilegeMap = new HashMap<String, Set<Privilege>>();

	private Long id;
	private String emailAddress;
	
	public BootPrincipal(Long id, String emailAddress) {
		this.id = id;
		this.emailAddress = emailAddress;
		
		// TODO: populate prvilegeMap based upon id
	}
	
	@Override
	public String getName() {
		return this.emailAddress;
	}

	public Map<String, Set<Privilege>> getAllKeyPrefixedPermissions(String... keyPrefixes) {
		List<String> prefixKeys = Arrays.asList(keyPrefixes);
		Map<String, Set<Privilege>> prefixedPermissions = new HashMap<String, Set<Privilege>>();
		
		for (String key: privilegeMap.keySet()) {
			for (String prefix: prefixKeys) {
				if (key.startsWith(prefix)) {
					prefixedPermissions.put(key, prefixedPermissions.get(key));
				}
			}
		}
		
		return prefixedPermissions;
	}
}
