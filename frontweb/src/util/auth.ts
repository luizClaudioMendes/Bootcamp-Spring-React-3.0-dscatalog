import jwtDecode from 'jwt-decode';
import { getAuthData } from './storage';

export type Role = 'ROLE_OPERATOR' | 'ROLE_ADMIN';

export type TokenData = {
  exp: number;
  user_name: string;
  authorities: Role[];
};

export const getTokenData = (): TokenData | undefined => {
  const loginResponse = getAuthData();

  try {
    return jwtDecode(loginResponse.access_token) as TokenData;
  } catch (error) {
    return undefined;
  }
};

export const isAuthenticated = (): boolean => {
  const tokenData = getTokenData();

  return tokenData && tokenData.exp * 1000 > Date.now() ? true : false;
};

export const hasAnyRoles = (roles: Role[]): boolean => {
  if (roles.length === 0) {
    return true;
  }

  const tokenData = getTokenData();

  // forma basica para resolver
  /* if (tokenData !== undefined) {
      for (var i = 0; i < roles.length; i++) {
        if(tokenData.authorities.includes(roles[i])) {
          return true;
        }
      }
    } */

  // forma high order do javascript para resolver
  if (tokenData !== undefined) {
    return roles.some((roles) => tokenData.authorities.includes(roles));
  }

  return false;
};
