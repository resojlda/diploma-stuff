package com.googlecode.vkapi;

import com.googlecode.vkapi.domain.OAuthToken;
import com.googlecode.vkapi.domain.user.VkUser;
import com.googlecode.vkapi.domain.user.VkUserSearch;
import com.googlecode.vkapi.exceptions.VkException;

import java.util.Collection;

/**
 * @author Aleksandr Stepanenko
 */
public interface VkApiExtension {

    Collection<VkUserSearch> getUsers(int universityId, OAuthToken authToken) throws VkException, InterruptedException;

}
