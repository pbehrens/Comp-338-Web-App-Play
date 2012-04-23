package domain
package objects

class Role(var isGuest:Boolean = true, var isMember:Boolean = false, var isStaff:Boolean = false, var isAdmin:Boolean = false) {
	def makeMember(){isMember = true}
	def makeStaff(){isStaff = true}
	def makeAdmin(){isAdmin = true}
	def removeMember(){isMember = false}
	def removeStaff(){isStaff = false}
	def removeAdmin(){isAdmin = false}
	
	def ==(check: Role): Boolean = (!(this.isGuest ^ check.isGuest)) && (!(this.isMember ^ check.isMember)) && (!(this.isStaff ^ check.isStaff)) && (!(this.isAdmin ^ check.isAdmin))
}